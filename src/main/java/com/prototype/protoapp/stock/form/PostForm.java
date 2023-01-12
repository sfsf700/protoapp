package com.prototype.protoapp.stock.form;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class PostForm {
    private int id;
    private String categoryForm;
    @NotNull(message = "日付を入力してください。")
    private Date ymdForm;
    @NotNull (message = "題名を入力してください。")
    @Size(min = 1, max = 25, message="25文字以内で入力してください。")
    private String titleForm;
    private int quantityForm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryForm() {
        return categoryForm;
    }

    public void setCategoryForm(String categoryForm) {
        this.categoryForm = categoryForm;
    }

    public Date getYmdForm() {
        return ymdForm;
    }

    public void setYmdForm(Date ymdForm) {
        this.ymdForm = ymdForm;
    }

    public String getTitleForm() {
        return titleForm;
    }

    public void setTitleForm(String titleForm) {
        this.titleForm = titleForm;
    }

    public int getQuantityForm() {
        return quantityForm;
    }

    public void setQuantityForm(int quantityForm) {
        this.quantityForm = quantityForm;
    }
}