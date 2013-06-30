package messaging;

public interface DestinationResolver<D> {

	D resolveDestination(String name);

}
