package cn.sofwin.app.service;

import cn.sofwin.app.dto.CollectionDto;
import cn.sofwin.app.entity.Collection;

import java.util.List;

public interface CollectionService {
    List<CollectionDto> selectCollectionByUserid(Integer id);

    void deleteCollectionById(Integer id);

    void insertCollection(Collection collection);
}
