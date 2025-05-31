import jetbrains.buildServer.configs.kotlin.v2019_2.*

version = "2025.2"

project {
    buildType(DjangoBuild)
}

object DjangoBuild : BuildType({
    name = "Django CI"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "Run CI Script"
            scriptContent = "./ci.sh"
        }
    }
})
