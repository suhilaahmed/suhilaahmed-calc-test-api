import random, json, logging
from locust import HttpUser, task, between, SequentialTaskSet, events


def validate_calculator_response(response):
    json_response = json.loads(response.text)
    equation_result = json_response["equationResult"]
    result_classifier = equation_result["numberClassifier"]

    if response.status_code == 200:
        if response.elapsed.total_seconds() < 1:
            if equation_result['resultNumber'] == 3.0:
                if result_classifier['primeNumber'] & result_classifier['naturalNumber'] \
                        & result_classifier['positiveNumber'] & result_classifier['wholeNumber'] & \
                        result_classifier['negativeNumber'] == False:
                    return True
    else:
        return False


class CalculatorService(SequentialTaskSet):
    def __init__(self, parent):
        super().__init__(parent)

    @task()
    def calculate_equation_per_user(self):
        print("calculate equation")
        user_id = str(random.choice(range(1, 2000)))
        with self.client.post("/calculate/" + user_id,
                              json={"equation": "1+2"},
                              headers={"Content-Type": "application/json"},
                              name="calculate equation", catch_response=True) as response:
            if validate_calculator_response(response):
                response.success()
            else:
                response.failure("Incorrect Response")


class CalculatorUser(HttpUser):
    host = "http://localhost:8080"
    wait_time = between(2, 5)
    tasks = [CalculatorService]


@events.quitting.add_listener
def _(environment, **kw):
    if environment.stats.total.fail_ratio > 0.05:
        logging.error("Test failed due to failure ratio > 5%")  # Success ration is 95%
        environment.process_exit_code = 1
    elif environment.stats.total.avg_response_time > 200:
        logging.error("Test failed due to average response time ratio > 200 ms")
        environment.process_exit_code = 1
    elif environment.stats.total.get_response_time_percentile(0.95) > 800:
        logging.error("Test failed due to 95th percentile response time > 800 ms")
        environment.process_exit_code = 1
    else:
        environment.process_exit_code = 0
