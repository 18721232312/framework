package com.bk.framework.extension.neo4j.model;

import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Relationship;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BK
 * @version V2.0
 * @description:
 * @date 2019-06-19 23:39
 */
public class GraphPageModel implements Serializable {

    private static final long serialVersionUID = 330410716100946538L;
    private int pageSize = 10;
    private int pageIndex = 1;
    private int prevPageIndex = 1;
    private int nextPageIndex = 1;
    private int pageCount = 0;
    private int pageFirstRowIndex = 1;
    private boolean hasNextPage = true;
    private int totalCount = 0;
    private long startTime = System.currentTimeMillis();
    private long endTime = System.currentTimeMillis();
    private List<Node> nodeList = new ArrayList<Node>();
    private List<Relationship> relationshipList = new ArrayList<Relationship>();

    /**
     * 分页对象构造函数
     *
     * @param pageSize 每页记录数
     */
    public GraphPageModel(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取分页记录数量
     *
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 获取当前页序号
     *
     * @return
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * 设置当前页序号
     *
     * @param pageIndex
     */
    public void setPageIndex(int pageIndex) {
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        this.pageIndex = pageIndex;
    }

    /**
     * 获取分页总数
     *
     * @return
     */
    public int getPageCount() {
        if (this.getTotalCount() == 0) {
            this.pageCount = 0;
        } else {
            int shang = this.getTotalCount() / this.getPageSize();
            int yu = this.getTotalCount() % this.getPageSize();
            if (yu > 0) {
                shang += 1;
            }
            this.pageCount = shang;
        }
        return pageCount;
    }

    /**
     * 获取每页的第一行序号
     *
     * @return
     */
    public int getPageFirstRowIndex() {
        this.pageFirstRowIndex = (this.pageIndex - 1) * this.getPageSize() + 1;
        return pageFirstRowIndex;
    }

    /**
     * 获取上一页序号
     *
     * @return
     */
    public int getPrevPageIndex() {
        if (this.pageIndex > 1) {
            this.prevPageIndex = this.pageIndex - 1;
        } else {
            this.prevPageIndex = 1;
        }
        return prevPageIndex;
    }

    /**
     * 获取下一页序号
     *
     * @return
     */
    public int getNextPageIndex() {
        if (this.pageIndex < this.pageCount) {
            this.nextPageIndex = this.pageIndex + 1;
        } else {
            this.nextPageIndex = this.pageCount;
        }
        return nextPageIndex;
    }

    /**
     * 跳转到下一页
     */
    public void nextPage() {
        if (this.totalCount == 0 || this.getPageCount() == 0) {
            this.pageIndex = 1;
        } else {
            if (this.pageIndex < this.pageCount) {
                this.pageIndex = this.pageIndex + 1;
            } else {
                this.pageIndex = this.pageCount;
            }
        }
    }

    /**
     * 跳转到上一页
     */
    public void prevPage() {
        if (this.pageIndex > 1) {
            this.pageIndex = this.pageIndex - 1;
        } else {
            this.pageIndex = 1;
        }
    }

    /**
     * 获取是否有下一页
     *
     * @return
     */
    public boolean isHasNextPage() {
        if (this.pageIndex < this.getPageCount()) {
            this.hasNextPage = true;
        } else {
            this.hasNextPage = false;
        }
        return hasNextPage;
    }

    /**
     * 获取总记录数
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 获取总记录数
     *
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 初始化起始时间（毫秒）
     */
    public void initStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    /**
     * 初始化截止时间（毫秒）
     */
    public void initEndTime() {
        this.endTime = System.currentTimeMillis();
    }

    /**
     * 获取毫秒格式的耗时信息
     *
     * @return
     */
    public String getTimeIntervalByMilli() {
        return String.valueOf(this.endTime - this.startTime) + "毫秒";
    }

    /**
     * 获取秒格式的耗时信息
     *
     * @return
     */
    public String getTimeIntervalBySecond() {
        double interval = (this.endTime - this.startTime) / 1000.0;
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(interval) + "秒";
    }

    /**
     * 打印时间信息
     */
    public void printTimeInfo() {
        System.out.println("起始时间：" + this.startTime);
        System.out.println("截止时间：" + this.endTime);
        System.out.println("耗费时间：" + this.getTimeIntervalBySecond());
    }

    /**
     * 获取Node检索结果列表
     *
     * @return
     */
    public List<Node> getNodeList() {
        return nodeList;
    }

    /**
     * 获取Relationship检索结果列表
     *
     * @return
     */
    public List<Relationship> getRelationshipList() {
        return relationshipList;
    }
}
