package com.gzz.createcode.template.vuex;

import java.util.List;

import com.gzz.createcode.common.Utils;
import com.gzz.createcode.mvc.model.Field;

public class Page {
	public static StringBuilder create(List<Field> fList, String clsUpp, String cName, String auth, String lowUpp) {
		StringBuilder sb = new StringBuilder();
		
		StringBuilder pageColum = new StringBuilder();
		StringBuilder cond = new StringBuilder();

		for (Field field : fList) {
			String name = field.getName();
			String comments = field.getComment();
			cond.append("\r\n      <FormItem label=\"" + comments + "\"><Input placeholder=\"请输入" + comments + "\" v-model=\"form." + name + "\"></Input></FormItem>");
			pageColum.append("\r\n          {title: '" + comments + "', key: '" + name + "'},");
		}
		sb.append(Utils.pageNote(cName + "列表", auth));
		sb.append("\r\n<template>");
		sb.append("\r\n  <div>");
		sb.append("\r\n  	<h3>" + cName + "管理</h3>");
		sb.append("\r\n    <hr/>");
		sb.append("\r\n    <Form inline :label-width=\"70\">");
		sb.append(cond);

		sb.append("\r\n      <FormItem>");
		sb.append("\r\n        <Button icon=\"search\" @click=\"refresh\" title=\"根据输入的条件查询\" type=\"primary\">查询</Button>");
		sb.append("\r\n        <Button type=\"primary\" icon=\"plus\" @click=\"addDialog()\" title=\"添加\" >添加</Button>");
		sb.append("\r\n      </FormItem>");
		sb.append("\r\n    </Form>");
		sb.append("\r\n    <Table :loading=\"loading\" :columns=\"tableHeader\" :data=\"dataList\" style=\"width: 100%\"></Table>");

		sb.append("\r\n    <br/>");
		sb.append("\r\n    <div style=\"text-align: right\" v-if=\"total > 0\">");
		sb.append("\r\n      <Page size=\"small\" :current=\"page\" :total=\"total\" show-total");
		sb.append("\r\n            @on-change=\"(curr) => {this.page = curr ; this.refresh();}\"></Page>");
		sb.append("\r\n    </div>");
		sb.append("\r\n    <" + clsUpp + "Dialog></" + clsUpp + "Dialog>");
		sb.append("\r\n  </div>");
		sb.append("\r\n</template>");
		sb.append("\r\n<script>");
		sb.append("\r\n  import " + clsUpp + "Dialog from './" + clsUpp + "Dialog.vue';");
		sb.append("\r\n  import " + clsUpp + "Expand from './" + clsUpp + "Expand.vue';");
		sb.append("\r\n  import {mapState, mapGetters, mapMutations, mapActions} from 'vuex';");
		sb.append("\r\n");
		sb.append("\r\n  export default {");
		sb.append("\r\n    components: {");
		sb.append("\r\n      " + clsUpp + "Dialog,");
		sb.append("\r\n      " + clsUpp + "Expand");
		sb.append("\r\n    },");
		sb.append("\r\n    data: function () {");
		sb.append("\r\n      const that = this;");
		sb.append("\r\n      return {");
		sb.append("\r\n        tableHeader: [");
		sb.append("\r\n          {");
		sb.append("\r\n            type: 'expand',");
		sb.append("\r\n            width: 50,");
		sb.append("\r\n            render: (h, params) => {");
		sb.append("\r\n              return h(" + clsUpp + "Expand, {");
		sb.append("\r\n                props: {row: params.row}");
		sb.append("\r\n              })");
		sb.append("\r\n            }");
		sb.append("\r\n          },");
		sb.append(pageColum);
		sb.append("\r\n          {");
		sb.append("\r\n            title: '操作',");
		sb.append("\r\n            width: 150,");
		sb.append("\r\n            render: (h, params) => {");
		sb.append("\r\n              return h('div', [");
		sb.append("\r\n                h('Button', {");
		sb.append("\r\n                  props: {type:'primary',size:'small'},");
		sb.append("\r\n                  style: {marginRight: '5px'},");
		sb.append("\r\n                  on: {");
		sb.append("\r\n                    click: () => {");
		sb.append("\r\n                      that.editDialog(params.row)");
		sb.append("\r\n                    }");
		sb.append("\r\n                  }");
		sb.append("\r\n                }, '编辑'),");
		sb.append("\r\n                h('Button', {");
		sb.append("\r\n                  props: {type: 'primary',size: 'small'},");
		sb.append("\r\n                  on: {");
		sb.append("\r\n                    click: () => {");
		sb.append("\r\n                      that.$store.dispatch('" + lowUpp + "/deleteAction', params.row)");
		sb.append("\r\n                    }");
		sb.append("\r\n                  }");
		sb.append("\r\n                }, '删除')");
		sb.append("\r\n              ]);");
		sb.append("\r\n            }");
		sb.append("\r\n          }");
		sb.append("\r\n        ]");
		sb.append("\r\n      }");
		sb.append("\r\n    },");
		sb.append("\r\n    computed: {");
		sb.append("\r\n      ...mapGetters({");
		sb.append("\r\n      }),");
		sb.append("\r\n      ...mapState({");
		sb.append("\r\n        form: (state) => state." + lowUpp + ".searchForm,");
		sb.append("\r\n        loading: (state) => state." + lowUpp + ".loading,");
		sb.append("\r\n        total: (state) => state." + lowUpp + ".total,");
		sb.append("\r\n        dataList: (state) => state." + lowUpp + ".dataList");
		sb.append("\r\n      }),");
		sb.append("\r\n      page: {");
		sb.append("\r\n        get() {");
		sb.append("\r\n          return this.$store.state." + lowUpp + ".page");
		sb.append("\r\n        },");
		sb.append("\r\n        set(value) {");
		sb.append("\r\n          this.$store.commit('" + lowUpp + "/setPage', value)");
		sb.append("\r\n        }");
		sb.append("\r\n      }");
		sb.append("\r\n    },");
		sb.append("\r\n    created: function () {");
		sb.append("\r\n      this.refresh();");
		sb.append("\r\n    },");
		sb.append("\r\n    methods: {");
		sb.append("\r\n      ...mapActions({}),");
		sb.append("\r\n      ...mapMutations(");
		sb.append("\r\n        {");
		sb.append("\r\n          refresh: '" + lowUpp + "/refresh',");
		sb.append("\r\n          addDialog: '" + lowUpp + "/addDialog',");
		sb.append("\r\n          editDialog: '" + lowUpp + "/editDialog',");
		sb.append("\r\n        }");
		sb.append("\r\n      ),");
		sb.append("\r\n    }");
		sb.append("\r\n  }");
		sb.append("\r\n</script>");
		sb.append("\r\n<style></style>");
		return sb;
	}
}
