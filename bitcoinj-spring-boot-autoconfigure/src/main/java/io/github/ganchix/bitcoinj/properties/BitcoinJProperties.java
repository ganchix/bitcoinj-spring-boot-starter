package io.github.ganchix.bitcoinj.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * BitcoinJ Properties.
 * <p>
 * Created by Rafael Ríos.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "bitcoinj")
public class BitcoinJProperties {

	/**
	 * Network the node is working, main net by default.
	 */
	private Network network = Network.MAIN_NET;

	/**
	 * Username for rpc.
	 */
	private String user;

	/**
	 * Password for rpc.
	 */
	private String password;

	/**
	 * Port used, defaults values: 8332 for main network and 18332 for tests networks .
	 */
	private Integer port;

	/**
	 * Host, must start with http, localhost for default
	 */
	private String host = "http://localhost";
}
