package com.example.pojo;

import com.example.constant.Result;
import lombok.*;

/**
 * @Author YSTen_SongJunBao
 * @Description: 分页参数
 * @Date 2022/1/26 15:04
 * @Version 1.0
 * com.example.pojo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnyUserResultRes extends Result {
    /**
     * 总数
     */
    private int total;
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 每页大小
     */
    private int pageSize;
}
