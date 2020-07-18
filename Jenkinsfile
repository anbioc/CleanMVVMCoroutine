pipeline {
  agent any
  stages {
    stage('Compile') {
      steps {
        sh './gradlew compileDebugSources'
        echo 'App Compiled'
      }
    }

    stage('Test') {
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

    stage('Build Time') {
      steps {
        sh './gradlew clean'
        sh './gradlew assembleDebug'
        archiveArtifacts '**/*.apk'
        echo 'Build Finished'
      }
    }

  }
}