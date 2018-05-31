package io.github.ganchix.bitcoinj.configuration;

import com.msgilligan.bitcoinj.rpc.BitcoinClient;
import io.github.ganchix.bitcoinj.health.BitcoinJHealthCheck;
import io.github.ganchix.bitcoinj.properties.BitcoinJProperties;
import io.github.ganchix.bitcoinj.properties.Network;
import org.bitcoinj.core.BlockChain;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.utils.BriefLogFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

/**
 * AutoConfiguration class.
 * <p>
 * Created by Rafa.
 */
@Configuration
@ConditionalOnClass({BlockChain.class})
@EnableConfigurationProperties(BitcoinJProperties.class)
public class BitcoinJAutoConfiguration {

	@Autowired
	private BitcoinJProperties properties;

	@Bean
	@ConditionalOnMissingBean
	public NetworkParameters params() {
		BriefLogFormatter.init();
		NetworkParameters params = MainNetParams.get();
		if (properties.getNetwork().equals(Network.TEST_NET)) {
			params = TestNet3Params.get();
		} else if (properties.getNetwork().equals(Network.REGTEST_NET)) {
			params = RegTestParams.get();
		} else if (properties.getNetwork().equals(Network.MAIN_NET)) {
			params = MainNetParams.get();
		}
		return params;
	}

	@Bean
	@ConditionalOnMissingBean
	public BitcoinClient bitcoinClient(NetworkParameters params) {

		if (!properties.getHost().startsWith("http")) {
			throw new RuntimeException("Host must start with http");
		}

		String port;

		if (properties.getPort() == null) {
			if (properties.getNetwork().equals(Network.MAIN_NET)) {
				port = "8332";
			} else {
				port = "18332";
			}
		} else {
			port = properties.getPort().toString();
		}
		return new BitcoinClient(params,
				URI.create(properties.getHost().concat(":").concat(port)),
				properties.getUser(), properties.getPassword());
	}

	@Bean
	@ConditionalOnMissingBean
	public BitcoinJHealthCheck bitcoinj(BitcoinClient bitcoinClient) {
		return new BitcoinJHealthCheck(bitcoinClient);
	}


}

