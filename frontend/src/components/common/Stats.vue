<template>
<div class="list">
        <el-scrollbar class="page-component__scroll">
            <el-main>
                <el-breadcrumb separator-class="el-icon-arrow-right">
                    <el-breadcrumb-item :to="{ path: '/' }">Homepage</el-breadcrumb-item>
                    <el-breadcrumb-item>Stats</el-breadcrumb-item>
                </el-breadcrumb>

            <chart-base></chart-base>
            </el-main>
        </el-scrollbar>
</div>
</template>

<script>
import ChartBase from './ChartBase.vue'

export default {
    data () {
        return {
        }
    },

    components: {
        ChartBase
    }
}
</script>
<style lang="scss" scoped>

.list{
    height: 100%;
    color: #48576a;
    .header-box {
		background-image: linear-gradient(-180deg, rgba(37,30,71,.9) 0%, rgba(36,83,155,.9) 100%);
		@media (max-width: 1249px) {
            width: 1080px !important;
            margin: 0 auto;
        }
    }
    .page-component__scroll{
        height: 100%;
        .el-breadcrumb{
            padding-bottom: 20px;
            border-bottom: 1px solid #ebeef5;
        }
    }
    .el-main{
        .el-breadcrumb{
            padding-bottom: 20px;
            border-bottom: 1px solid #ebeef5;
        }
        .title-section{
            background: #f0f2f5;
            padding: 10px;
            margin: 20px 0;
            position: relative;
            color: #606266;
            .item-group{
                display: inline-block;
                margin-right: 16px;
                label{
                    padding-right: 6px;
                }
            }
            .el-radio-button__inner{
                padding: 10px
            }
            .el-input{
                width: 230px;
            }
        }
        .query-button{
            position: absolute;
            right:90px;
        }
        .add-button{
            position: absolute;
            right: 10px;
        }
        .el-button{
            a{
                text-decoration: none;
                color: #fff;
            }
            &:hover{

            }
        }
        .el-table{
            color: #606266;
            thead{
                 color: #606266;
            }
        }
        .el-pagination{
            margin-top:30px;
            text-align: right;
        }
    }
}

</style>
