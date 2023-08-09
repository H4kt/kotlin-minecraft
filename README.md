# kotlin-minecraft
A simple wrapper of most of the commonly used kotlin libraries for Bukkit and Bungeecord

## ⚠️ Java 8 is no longer supported due to Exposed requiring Java 11+ 

## Roadmap
- [ ] Include other modules of Exposed: kotlin-datetime, json, crypt
- [ ] Automate builds & releases based on uploads to Maven Central

## Installation
In your plugin.yml add a dependecy to a corresponding module that you are using in your code:
```yml
#other plugin descriptor stuff
depend: [<module name goes here>]
```

Add required modules into your plugins folder

## Available modules:
### [Kotlin](https://github.com/JetBrains/kotlin)
* kotlin-stdlib - Kotlin standart library (required)
* kotlin-reflect - Kotlin reflection library

### [Kotlinx datetime](https://github.com/Kotlin/kotlinx-datetime)
* kotlinx-datetime - Kotlinx datetime implementation

### [Kotlinx coroutines](https://github.com/Kotlin/kotlinx.coroutines)
* kotlinx-coroutines - Kotlinx coroutines implementation

### [Kotlinx serialization](https://github.com/Kotlin/kotlinx.serialization)
* kotlinx-serialization-core - Kotlinx serialization core implementation (required for any other serialization module)
* kotlinx-serialization-json - Kotlinx json seriazation implementation

### [Exposed](https://github.com/JetBrains/Exposed)
* exposed-core - Exposed core implementation (required for any other exposed module)
* exposed-jdbc - Exposed jdbc implementation
* exposed-dao - Exposed dao implementation
