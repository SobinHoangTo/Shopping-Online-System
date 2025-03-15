/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DTOs;

/**
 *
 * @author vdqvi
 */
public class GeneralQueryParam {

    public int pageIndex;
    public int pageSize;
    public String search;
    public String sortBy;
    public String sortOrder;

    public GeneralQueryParam(int pageIndex, int pageSize, String search, String sortBy, String sortOrder) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.search = search;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        return "GeneralQueryParam{" + "pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", search=" + search + ", sortBy=" + sortBy + ", sortOrder=" + sortOrder + '}';
    }
}
