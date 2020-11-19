node {
    stage('CLONE SOURCE CODE') {
        git branch: 'master', url: 'https://github.com/suhilaahmed/suhilaahmed-calc-test-api.git'
        }

    stage('run unit tests') {
        sh 'cd equation_calculator && /usr/local/bin/mvn test'
        }

    stage('run maven') {
        sh 'cd equation_calculator && nohup /usr/local/bin/mvn spring-boot:run &'
        }

    stage('load test') {
        sh 'cd load_test && /usr/local/bin/locust --config=master.conf'
        }
}