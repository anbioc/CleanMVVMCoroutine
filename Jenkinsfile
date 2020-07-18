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
        sh './gradlew lintDemoEuDebug'
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
        archiveArtifacts '**/*.apk'
        echo 'Build Finished'
        sh './gradlew assembleDebug'
      }
    }

  }
}