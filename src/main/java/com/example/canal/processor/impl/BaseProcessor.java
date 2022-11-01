//package com.example.canal.processor.impl;
//
//import com.alibaba.otter.canal.protocol.CanalEntry;
//import com.example.canal.processor.Processor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.util.CollectionUtils;
//
//import java.lang.reflect.ParameterizedType;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * @Author SongJunBao
// * @Description:
// * @Date 2022/4/19 18:25
// * @Version 1.0
// * com.example.canal.processor.impl
// */
//@Slf4j
//@SuppressWarnings("Duplicates")
//public abstract class BaseProcessor<T> implements Processor {
//    protected Map<String, String> map = new HashMap<>();
//    private static final Pattern COLUMN_SPLIT = Pattern.compile("_[a-z|A-Z]");
//
//    protected void save(List<CanalEntry.Column> columns) {
//        T entity = fromColumns(columns);
//        if (null == entity) {
//            return;
//        }
//        log.debug("canal listener save sync table={},Method:save,params:entity={}", tableName(), entity);
//        doSave(entity);
//    }
//
//    protected void delete(List<CanalEntry.Column> columns) {
//        T entity = fromColumns(columns);
//        if (null == entity) {
//            return;
//        }
//        log.debug("canal listener delete table={},Method:delete,params:entity={}", tableName(), entity);
//        doDelete(entity);
//    }
//
//    protected void update(List<CanalEntry.Column> afterColumns, List<CanalEntry.Column> beforeColumns) {
//        T afterEntity = fromColumns(afterColumns);
//        if (null == afterEntity) {
//            return;
//        }
//        T beforeEntity = fromColumns(beforeColumns);
//        if (null == beforeEntity) {
//            return;
//        }
//        log.debug("canal listener update table={},Method:update,params:afterEntity={},beforeEntity={}", tableName(), afterEntity, beforeEntity);
//        if (!CompareUtils.compareIsUpdate(afterEntity, beforeEntity)) {
//            log.debug("no update before: {}, after: {}", beforeEntity, afterEntity);
//            return;
//        }
//        doUpdate(afterEntity, beforeEntity);
//    }
//
//    /**
//     * save
//     *
//     * @param entity entity
//     */
//    public abstract void doSave(T entity);
//
//    /**
//     * update
//     *
//     * @param afterEntity  afterEntity
//     * @param beforeEntity beforeEntity
//     */
//    public abstract void doUpdate(T afterEntity, T beforeEntity);
//
//    /**
//     * delete
//     *
//     * @param entity entity
//     */
//    public abstract void doDelete(T entity);
//
//    @Override
//    public int process(CanalEntry.Entry entry) {
//        CanalEntry.RowChange rowChange;
//        try {
//            rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
//            List<CanalEntry.RowData> rowDataList = rowChange.getRowDatasList();
//            CanalEntry.EventType eventType = rowChange.getEventType();
//
//            log.debug("event type : {}, and SQL is :{}.", eventType, rowChange.getSql());
//
//            for (CanalEntry.RowData rowData : rowDataList) {
//                if (CanalEntry.EventType.DELETE.equals(eventType)) {
//                    delete(rowData.getBeforeColumnsList());
//                } else if (CanalEntry.EventType.INSERT.equals(eventType)) {
//                    save(rowData.getAfterColumnsList());
//                } else if (CanalEntry.EventType.UPDATE.equals(eventType)) {
//                    update(rowData.getAfterColumnsList(), rowData.getBeforeColumnsList());
//                }
//            }
//            return rowDataList.size();
//        } catch (Exception e) {
//            log.error("edge canal process failed parse event has an error", e);
//        }
//        return 0;
//    }
//
//    protected T fromColumns(List<CanalEntry.Column> columns) {
//        @SuppressWarnings("unchecked")
//        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//        return createBean(columns, clazz);
//    }
//
//    protected T createBean(List<CanalEntry.Column> columns, Class<T> cls) {
//        try {
//            Object entity = cls.newInstance();
//           // Object entity = BeanHelper.newInstance(cls);
//            for (CanalEntry.Column column : columns) {
//                if (StringUtils.isEmpty(column.getValue())) {
//                    continue;
//                }
//                String columnName = column.getName();
//                String fieldName = getProperty(columnName);
//                BeanHelper.setValue(cls, entity, fieldName, column.getValue());
//            }
//            //noinspection unchecked
//            return (T) entity;
//        } catch (Exception e) {
//            log.error("create newInstance error", e);
//            return null;
//        }
//    }
//
//    /**
//     * 字段转换成对象属性 例如：user_name to userName
//     *
//     * @return string
//     */
//    protected static String columnToProperty(String column) {
//        StringBuffer sb = new StringBuffer();
//        Matcher m = COLUMN_SPLIT.matcher(column.toLowerCase());
//        while (m.find()) {
//            m.appendReplacement(sb, m.group().toUpperCase());
//        }
//        m.appendTail(sb);
//        return sb.toString().replace("_", "");
//    }
//
//    public String getProperty(String column) {
//        String property = null;
//        if (!CollectionUtils.isEmpty(map)) {
//            property = map.get(column);
//        }
//        if (StringUtils.isEmpty(property)) {
//            property = columnToProperty(column);
//        }
//        return property;
//    }
//}
