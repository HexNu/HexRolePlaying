package hex.rpg.service.command;

import hex.rpg.service.support.PuHandlerFactory;

/**
 * Created 2014-12-10
 *
 * @author hl
 * @param <T>
 */
public interface ServiceCommand<T> {

    T execute();

    void setFingerprint(String fingerprint);

    void setPuHandlerFactory(PuHandlerFactory puHandlerFactory);

    Object getSynchronizationObject();
}
