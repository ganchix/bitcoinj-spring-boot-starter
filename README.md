# Spring boot starter BitcoinJ [![Build Status](https://travis-ci.org/ganchix/bitcoinj-spring-boot-starter.svg?branch=master)](https://travis-ci.org/ganchix/bitcoinj-spring-boot-starter) [![codecov](https://codecov.io/gh/ganchix/bitcoinj-spring-boot-starter/branch/master/graph/badge.svg)](https://codecov.io/gh/ganchix/bitcoinj-spring-boot-starter) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.ganchix/bitcoinj-spring-boot-parent/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/io.github.ganchix/bitcoinj-spring-boot-parent) [![GitHub stars](https://img.shields.io/github/stars/badges/shields.svg?style=social&label=Star)](https://github.com/ganchix/bitcoinj-spring-boot-starter)

Spring boot starter for use [BitcoinJ Rpc Client](https://github.com/ConsensusJ/consensusj) in a Spring Boot way.

# Table of Contents
 
- [Overview](#overview)
- [Getting started](#getting-started)
- [License](#license)


### Overview

This implementation offers a way to use [BitcoinJ Rpc Client](https://github.com/ConsensusJ/consensusj) like a spring boot starter project.


### Getting started

#### Add dependency

```xml
<dependency>
    <groupId>io.github.ganchix</groupId>
    <artifactId>bitcoinj-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>

```
#### Code example

Start your bitcoin node, create your spring boot project and add the dependency, configure in the properties file your database,
 you can see [BitcoinJProperties](https://github.com/ganchix/bitcoinj-spring-boot-starter/blob/master/bitcoinj-spring-boot-autoconfigure/src/main/java/io/github/ganchix/bitcoinj/properties/BitcoinJProperties.java) to check all options available.

Example:

```properties
bitcoinj.password=openSesame
bitcoinj.network=REGTEST_NET
```

And now you can autowired the client:

```java
	@Autowired
	private BitcoinClient bitcoinClient;
```


### License

Spring boot starter BitcoinJ is licensed under the MIT License. See [LICENSE](LICENSE.md) for details.

Copyright (c) 2018 Rafael Ríos Moya
