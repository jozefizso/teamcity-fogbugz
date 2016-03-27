TeamCity FogBugz issue tracker integration
==============================================================

[![Travis](https://img.shields.io/travis/jozefizso/teamcity-fogbugz.svg)](https://travis-ci.org/jozefizso/teamcity-fogbugz)
[![Coverage Status](https://img.shields.io/coveralls/jozefizso/teamcity-fogbugz.svg)](https://coveralls.io/github/jozefizso/teamcity-fogbugz?branch=master)
![MIT License](https://img.shields.io/github/license/jozefizso/teamcity-fogbugz.svg)

This plugin enables TeamCity to integrate with FogBugz using
issue tracker API.

Supported TeamCity servers:

* TeamCity 9.1 (Java 1.7 and newer)


## Install
To install the plugin, put zip archive to **plugins** directory
under TeamCity data directory.

## Contributing

### 1. Build
Issue `mvn package` command from the root project to build the plugin.
Resulting package will be placed in **target** directory. 

### 2. Run

The `tc-sdk:start` goal will automatically download and run the TeamCity server
and install the plugin.

```
mvn package tc-sdk:start
```

[TeamCity plugin goals documentation](https://github.com/JetBrains/teamcity-sdk-maven-plugin#plugin-goals)


## License

Copyright (c) 2015 Jozef Izso under [MIT License](LICENSE)
