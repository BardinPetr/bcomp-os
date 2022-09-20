plugins {
    application
}

dependencies {
    implementation(files("libs/pokemon.jar"))
}

application {
    mainClass.set("ru.bardinpetr.itmo.lab_2.App")
}

tasks.jar {
    manifest {
        attributes(mapOf("Main-Class" to "ru.bardinpetr.itmo.lab_2.App"))
    }
}