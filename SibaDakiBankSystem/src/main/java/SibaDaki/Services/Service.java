package SibaDaki.Services;

import java.util.List;

/**
 * Created by User on 2016/08/29.
 */
public interface Service<S,ID> {

    public S findById(ID id);

    public S save(S entity);

    public S update(S entity);

    public void delete(S entity);

    public List<S> findAll();
}

