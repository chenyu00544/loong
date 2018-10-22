var app = new Vue({
    el: '#appFour',
    data: {
        token: $('input[name=_token]').val(),
        goods_id: $('input[name=goods_id]').val(),
        goods_type: $('input[name=goods_type]').val(),
        model_price: $('input[name=goods_model]').val(),
        productUrl: productUrl,
        goodsAttrUrl: goodsAttrUrl,
        getAttributesUrl: getAttributesUrl,
        getCatesUrl: getCatesUrl,
        getTypeCatesUrl: getTypeCatesUrl,
        getGoodsTypeUrl: getGoodsTypeUrl,
        getGoodsAttrUrl: getGoodsAttrUrl,
        products: [],
        goodsTypes: [],
        goodsTypeCatesSelect: [],
        goodsTypeCates: [],
        goodsAttrImg: [],
        goodsAttrMSelect: [],
        goodsAttrM: [],
        goodsAttrOSelect: [],
        goodsAttrO: []
    },

    mounted: function () {
        this.initData();
    },

    methods: {
        initData: function () {
            var that = this;
            $.post(this.productUrl + this.goods_id, {
                _token: this.token,
                model_price: this.model_price
            }, function (data) {
                that.products = data.products;
                that.goodsAttrImg = data.goods_attr_img;
            });
            $.post(this.getTypeCatesUrl + this.goods_type, {
                _token: this.token
            }, function (data) {
                that.goodsTypes = data.goodsTypes;
                that.goodsTypeCates = data.goodsTypeCates;
                for (var i = 0; i < data.goodsTypeCates.length; i++) {
                    for (var j = 0; j < data.goodsTypeCates[i].length; j++) {
                        if (data.goodsTypeCates[i][j].select == 1) {
                            that.goodsTypeCatesSelect.push(data.goodsTypeCates[i][j].cate_id);
                        }
                    }
                }
            });
            $.post(this.getGoodsAttrUrl + this.goods_id, {
                _token: this.token
            }, function (data) {
                that.goodsAttrM = data.goods_attr_m;
                that.goodsAttrO = data.goods_attr_o;
            });
        },
        loading: function (bool) {
            layer.load();
            if (bool) {
                layer.closeAll('loading');
            } else {
                setTimeout(function () {
                    layer.closeAll('loading');
                }, 10000);
            }
        },
        goodsTypeCate: function (e) {
            this.getNextGoodsTypeCate(e.target);
            this.getGoodsTypes(e.target);
        },
        goodsTypeChange: function (e) {
            var that = this;
            var cat_id = $(e.target).val();
            $('input[name=goods_type]').val(cat_id);
            $.post(this.getAttributesUrl + cat_id, {_token: this.token}, function (data) {
                var attrM = [];
                var attrO = [];
                $.each(data, function (k, v) {
                    if (v.attr_type == 1) {
                        that.goodsAttrMSelect = [];
                        attrM.push(v);
                    } else {
                        that.goodsAttrOSelect.push([]);
                        attrO.push(v)
                    }
                });
                that.goodsAttrM = attrM;
                that.goodsAttrO = attrO;
            });
        },
        selectValue: function (e) {
            var attr_values = [];
            var attr_v = [];
            var attr_id = 0;
            $('.attr-multi').find('.checkbox-inline input').each(function (k, v) {
                if ($(v).is(':checked')) {
                    if ($(v).data('attr_id') == attr_id) {
                        attr_v.push([$(v).val(), attr_id]);
                    } else {
                        if (attr_v.length > 0) {
                            attr_values.push(attr_v);
                        }
                        attr_v = [];
                        attr_id = $(v).data('attr_id');
                        attr_v.push([$(v).val(), attr_id]);
                    }
                }
                if (k == $('.attr-multi').find('.checkbox-inline input').length - 1) {
                    attr_values.push(attr_v);
                }
            });
            var that = this;
            $.post(this.productUrl + this.goods_id, {
                _token: this.token,
                model_price: this.model_price,
                attr_values: attr_values
            }, function (data) {
                that.products = data.products;
                that.goodsAttrImg = data.goods_attr_img;
            });
        },
        attrSortChange: function (e) {
            var that = this;
            that.loading(false);
            var sort = $(e.target).val()
                , goodsAttrId = $(e.target).data('goodsattrid');
            $.post(this.goodsAttrUrl, {
                id: goodsAttrId,
                _token: this.token,
                attr_sort: sort,
            }, function (data) {
                that.loading(true);
            });
        },
        upLoadAttrImg: function (e) {
            var that = this;
            that.loading(false);
            var imgFile = $(e.target)[0].files[0]
                , goodsAttrId = $(e.target).data('goodsattrid')
                , oldattrimg_o = $(e.target).data('oldattrimg_o')
                , oldattrimg_t = $(e.target).data('oldattrimg_t')
                , form = new FormData();
            form.append('attr_img', imgFile);
            form.append('id', goodsAttrId);
            form.append('old_attr_img_o', oldattrimg_o);
            form.append('old_attr_img_t', oldattrimg_t);
            form.append('_token', this.token);
            $.ajax({
                url: this.goodsAttrUrl,
                type: "POST",
                data: form,
                contentType: false,
                processData: false,
                success: function (data) {
                    if (data.code == 1) {
                        that.initData();
                    }
                    that.loading(true);
                }
            });
        },
        getNextGoodsTypeCate: function (dom) {
            var that = this;
            var id = $(dom).val();
            var level = $(dom).data('level');
            if (id > 0) {
                $.post(this.getCatesUrl + id, {_token: this.token}, function (data) {
                    if (data.data.length > 0) {
                        Vue.set(that.goodsTypeCatesSelect, level + 1, 0);
                        Vue.set(that.goodsTypeCates, level + 1, data.data);
                    }
                });
            }
        },
        getGoodsTypes: function (dom) {
            var that = this;
            var id = $(dom).val();
            $.post(this.getGoodsTypeUrl + id, {_token: this.token}, function (data) {
                that.goodsTypes = data;
                that.goods_type = 0;
            });
        }
    },

    delimiters: ['${', '}']
});