pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh './gradlew compileDebugSources'
        echo 'App Compiled'
      }
    }

    stage('test') {
      steps {
        sh './gradlew testDemoEuDebugUnitTest'
        echo 'Lint check done!'
      }
    }

    stage('Lint') {
      steps {
        sh './gradlew lintDemoEuDebug'
        echo 'Lint Chacked.'
      }
    }

    stage('Buisld Time') {
      steps {
        sh './gradlew clean'
        sh './gradlew assembleDebug'
        archiveArtifacts '**/*.apk'
        echo 'Build Finished'
      }
    }

  }
}