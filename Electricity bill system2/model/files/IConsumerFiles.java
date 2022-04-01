package files;

import consumer.Consumer;
import eb.ElectricityBoard;

public interface IConsumerFiles {
	public void createConsumer(Consumer consumer);
	public void updateConsumer(Consumer consumer);
	public void loadConsumersToEb(ElectricityBoard eb);
}
