package cn.sofwin.app.service.imp;

import cn.sofwin.app.dao.CollectionDao;
import cn.sofwin.app.dto.CollectionDto;
import cn.sofwin.app.entity.Collection;
import cn.sofwin.app.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("collectionService")
public class CollectionServiceImp implements CollectionService {

    @Autowired
    CollectionDao collectionDao;
    @Override
    public List<CollectionDto> selectCollectionByUserid(Integer id) {

        List<CollectionDto> list = collectionDao.findCollectionByUserid(id);

        return list;
    }

    /**
     * 删除一个收藏
     * @param id
     */
    @Override
    public void deleteCollectionById(Integer id) {
        collectionDao.deleteCollectionById(id);
    }

    /**
     * 新增收藏
     * @param collection
     */
    @Override
    public void insertCollection(Collection collection) {
        collectionDao.insertCollection(collection);
    }
}
