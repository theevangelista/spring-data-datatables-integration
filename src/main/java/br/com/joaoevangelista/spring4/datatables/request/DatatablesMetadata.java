package br.com.joaoevangelista.spring4.datatables.request;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Representa parametros de request da datatables
 * @author João Pedro Evangelista
 */

public class DatatablesMetadata {

    private  String search;

    private Integer start;

    private Integer lenght;

    private Integer draw;

    public DatatablesMetadata(Integer start, Integer lenght, String search, Integer draw) {
        this.start = start;
        this.lenght = lenght;
        this.search = search;
        this.draw = draw;
    }

    public DatatablesMetadata() {
    }

    public static DatatablesMetadata fromRequest(HttpServletRequest request) {
        if (request != null) {

            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()){
                System.out.println(parameterNames.nextElement());
            }

            String startParam = request.getParameter(Constants.START);
            String lenghtParam = request.getParameter(Constants.LENGTH);
            String drawParam = request.getParameter(Constants.DRAW);
            String searchParam = request.getParameter(Constants.SEARCH);
            Integer start = StringUtils.isEmpty(startParam) ? 0 : Integer.parseInt(startParam);
            Integer lenght = StringUtils.isEmpty(lenghtParam) ? 10 : Integer.parseInt(lenghtParam) == 0 ? 10 : Integer.parseInt(lenghtParam);
            Integer draw = StringUtils.isEmpty(drawParam) ? 1 : Integer.parseInt(drawParam);
            String search = searchParam == null ? "" : searchParam;
            return new DatatablesMetadata(start, lenght, search,draw);
        }
        return new DatatablesMetadata();
    }

    public int getCurrentPageIndex() {
        return Math.max(0, this.start / this.lenght);
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLenght() {
        return lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DatatablesMetadata that = (DatatablesMetadata) o;

        if (draw != null ? !draw.equals(that.draw) : that.draw != null) return false;
        if (lenght != null ? !lenght.equals(that.lenght) : that.lenght != null) return false;
        if (search != null ? !search.equals(that.search) : that.search != null) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = search != null ? search.hashCode() : 0;
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (lenght != null ? lenght.hashCode() : 0);
        result = 31 * result + (draw != null ? draw.hashCode() : 0);
        return result;
    }
}
