# Home Assignment: Scratch Game #

Build: gradle clean build

# Running the Game #

To run the game, ensure you have the correct version of Java installed (Java 8 or above). Compile the source code and
execute the `ScratchGameApp` class with the required arguments:

```
java -jar <java-jar-file> --config <path_to_config_file> --betting-amount <bet_amount>
```

java -jar build/libs/HomeAssignment-1.0-SNAPSHOT.jar --config config.json --betting-amount 100

- `<path_to_config_file>`: The path to the JSON configuration file for the game.
- `<bet_amount>`: The betting amount for the game round.
- 