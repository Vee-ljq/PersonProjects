package cn.sofwin.app.dao;

import cn.sofwin.app.dto.CollectionDto;
import cn.sofwin.app.entity.Collection;

import java.util.List;

public interface CollectionDao {

    List<CollectionDto> findCollectionByUserid(Integer id);

    void deleteCollectionById(Integer id);

    void insertCollection(Collection collection);
}
