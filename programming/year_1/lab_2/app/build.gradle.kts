plugins {
    application
}

dependencies {
    implementation(files("libs/pokemon.jar"))
}

application.mainClass.set("ru.bardinpetr.itmo.lab_2.App")

val fatJar = task("fatJar", type = Jar::class) {
    archiveBaseName.set("${project.name}_fat")
    manifest {
        attributes(mapOf("Main-Class" to application.mainClass))
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClass,
                "Class-Path" to "pokemon.jar"
            )
        )
    }
}