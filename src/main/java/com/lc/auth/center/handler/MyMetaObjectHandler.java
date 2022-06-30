package com.lc.auth.center.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneId;
import static com.lc.auth.center.enums.TZoneEnum.SHANGHAI;

/**
 * @author: lucheng
 * @data: 2022/4/30 16:14
 * @version: 1.0
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"dtCreated", LocalDateTime.class,
                LocalDateTime.now(ZoneId.of(SHANGHAI.getZone())));
        this.strictInsertFill(metaObject,"dtModified", LocalDateTime.class,
                LocalDateTime.now(ZoneId.of(SHANGHAI.getZone())));
        // 如果表中没有invalid字段，则不执行这个操作。 0表示invalid 1表示valid
        this.strictInsertFill(metaObject, "invalid", Integer.class, 1);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,"dtModified", LocalDateTime.class,
                LocalDateTime.now(ZoneId.of(SHANGHAI.getZone())));
    }


}
