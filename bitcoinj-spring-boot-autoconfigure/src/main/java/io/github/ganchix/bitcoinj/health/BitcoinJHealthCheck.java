package io.github.ganchix.bitcoinj.health;

import com.msgilligan.bitcoinj.rpc.BitcoinClient;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * Health check used to check the connection with the bitcoin node.
 * <p>
 * Created by Rafael Ríos
 */
public class BitcoinJHealthCheck extends AbstractHealthIndicator {

	/**
	 * Bitcoin client.
	 */
	private final BitcoinClient bitcoinClient;

	public BitcoinJHealthCheck(BitcoinClient bitcoinClient) {
		Assert.notNull(bitcoinClient, "BitcoinClient must not be null");
		this.bitcoinClient = bitcoinClient;
	}

	@Override
	protected void doHealthCheck(Health.Builder builder) throws IOException {

		builder.withDetail("netVersion", bitcoinClient.getNetworkInfo().getVersion());
		builder.withDetail("blockNumber", bitcoinClient.getBlockChainInfo().getBlocks());
		builder.withDetail("difficulty", bitcoinClient.getBlockChainInfo().getDifficulty());
		builder.withDetail("chain", bitcoinClient.getBlockChainInfo().getChain());
		builder.status(Status.UP);
	}
}
