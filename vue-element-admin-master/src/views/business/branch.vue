<template>
  <div class="app-container">

    <el-row :gutter="20">
      <el-col :span="6" :offset="21">
        <el-button
          class="filter-item"
          style="margin-left: 10px;"
          type="primary"
          @click="handleCreate"
        >
          增加
        </el-button>
      </el-col>
    </el-row>

    <el-table :data="tableData" style="width: 100%;margin-top: 15px;">
      <el-table-column prop="branch_no" label="编号" width="150" />
      <el-table-column prop="name" label="分院名称" width="250" />
      <el-table-column prop="address" label="分院地址" />
      <el-table-column label="操作" width="250">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="增加" :visible.sync="dialogFormVisible">
      <el-form
        ref="numberValidateForm"
        :model="numberValidateForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item
          label="编号"
          prop="branch_no"
          :rules="[{ required: true, message: '编号不能为空' },{ type: 'number', message: '编号必须为数字值' }]"
        >
          <el-col>
            <el-input
              v-model.number="numberValidateForm.branch_no"
              type="branch_no"
              autocomplete="off"
            />
          </el-col>
        </el-form-item>
        <el-form-item
          label="分院名称"
          prop="name"
          :rules="[{ required: true, message: '分院名称不能为空' }]"
        >
          <el-col>
            <el-input
              v-model.number="numberValidateForm.name"
              type="name"
              autocomplete="off"
            />
          </el-col>
        </el-form-item>
        <el-form-item
          label="分院地址"
          prop="address"
          :rules="[{ required: true, message: '分院地址不能为空' }]"
        >
          <el-col>
            <el-input
              v-model.number="numberValidateForm.address"
              type="address"
              autocomplete="off"
            />
          </el-col>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('numberValidateForm')">提交</el-button>
          <el-button @click="resetForm('numberValidateForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { getBranch, addBranch } from '@/api/branch'
export default {
  data() {
    return {
      tableData: [],
      dialogFormVisible: false,
      numberValidateForm: {
        branch_no: '',
        name: '',
        address: ''
      }
    }
  },
  mounted() {
    this.branchQryAction()
  },
  methods: {
    branchQryAction() {
      getBranch().then(res => {
        console.log(res)
        this.tableData = res.data
        console.log(this.tableData)
      })
    },
    handleEdit(index, row) {
      console.log(index, row)
    },
    handleDelete(index, row) {
      console.log(index, row)
    },
    handleCreate() {
      this.dialogFormVisible = true
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          const temp = { 'branch_no': this.numberValidateForm.branch_no.toString(),
            'name': this.numberValidateForm.name,
            'address': this.numberValidateForm.address
          }
          console.log(temp)
          addBranch(temp).then(res => {
            console.log()
            alert('submit!')
            this.dialogFormVisible = false
          })
        } else {
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
