package io.github.ganchix.arangodb.configuration;

import io.github.ganchix.bitcoinj.configuration.BitcoinJAutoConfiguration;
import io.github.ganchix.bitcoinj.health.BitcoinJHealthCheck;
import org.bitcoinj.core.NetworkParameters;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.testcontainers.containers.GenericContainer;

import static org.assertj.core.api.Assertions.assertThat;

public class BitcoinJAutoConfigurationTests {

	@ClassRule
	public static GenericContainer bitcoin =
			new GenericContainer("ruimarinho/bitcoin-core:latest")
					.withExposedPorts(18444)
					.withCommand("-regtest","-printtoconsole","-rpcallowip=0.0.0.0/0",
							"-server=1", "-rest=0","-rpcpassword=admin",
							"-rpcuser=admin", "-disablewallet=1","-rpcport=18444"
									,"-rpcbind=:18444");


	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(BitcoinJAutoConfiguration.class));



    @Test
    public void checkIfBitcoinJIsConnected() {
        this.contextRunner
                .withPropertyValues("bitcoinj.network=REGTEST_NET","bitcoinj.host=http://".concat(bitcoin.getContainerIpAddress()),
                        "bitcoinj.port=".concat(bitcoin.getMappedPort(18444).toString()),
		                "bitcoinj.user=admin", "bitcoinj.password=admin")
                .run((context) -> {
                    BitcoinJHealthCheck bitcoinJHealthCheck = context.getBean(BitcoinJHealthCheck.class);
                    bitcoinJHealthCheck.health();
                    assertThat(context).hasSingleBean(NetworkParameters.class);
                });
    }


}
