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
        echo 'tests done!'
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
        sh './gradlew assembleRelease'
        archiveArtifacts '**/*.apk'
        echo 'Build Finished'
      }
    }

  }
}