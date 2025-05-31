import jetbrains.buildServer.configs.kotlin.v2019_2.*

object DjangoBuild : BuildType({
    name = "Django CI/CD Pipeline"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "Set up Python venv and install dependencies"
            scriptContent = """
                python3 -m venv venv
                source venv/bin/activate
                pip install -r requirements.txt
            """.trimIndent()
        }

        script {
            name = "Run flake8"
            scriptContent = """
                source venv/bin/activate
                flake8 app/
            """.trimIndent()
        }

        script {
            name = "Run pytest with coverage"
            scriptContent = """
                source venv/bin/activate
                coverage run -m pytest
                coverage xml
            """.trimIndent()
        }

        script {
            name = "Run SonarScanner"
            scriptContent = """
                source venv/bin/activate
                sonar-scanner
            """.trimIndent()
        }
    }

    triggers {
        vcs {
            id = "vcsTrigger"
        }
    }

    requirements {
        exists("python3")
    }
})
