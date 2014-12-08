package br.com.joaoevangelista.spring4.datatables;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Responde para datatables v1.10+
 *
 * @author João Pedro Evangelista
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageEntity<T> implements Serializable {
    /**
     * Informa a datatables a corrente de renderização
     * quando muda, ela atualiza a table
     */
    private int draw;

    /**
     * @see org.springframework.data.domain.Page#getNumberOfElements()
     */
    private int recordsTotal;

    /**
     * Total depois do filtro
     *
     * @see org.springframework.data.domain.Page#getTotalElements()
     */
    private long recordsFiltered;

    /**
     * Recebe o array de resultados do db
     *
     * @see org.springframework.data.domain.Page#getContent()
     */
    private List<T> data;

    /**
     * Constroi nova resposta para a Datatables
     */
    public PageEntity(int draw, int recordsTotal, long recordsFiltered, List<T> data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageEntity that = (PageEntity) o;

        if (draw != that.draw) return false;
        if (recordsFiltered != that.recordsFiltered) return false;
        if (recordsTotal != that.recordsTotal) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = draw;
        result = 31 * result + recordsTotal;
        result = 31 * result + (int) (recordsFiltered ^ (recordsFiltered >>> 32));
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("[The current draw is %s with %s recordsTotal and %s recordsFiltered]" +
                                     " [The current received data is %s",
                             draw, recordsTotal, recordsFiltered, data.toString());
    }


}
