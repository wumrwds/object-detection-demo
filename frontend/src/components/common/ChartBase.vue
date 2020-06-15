<template>
<div class="list">
    <div>
        <div class="title-section">
            <div class="item-group">
                <label>Selected Cameras：</label>
                <el-select v-model="selectedCameras" multiple filterable :disabled="isCameraDisabled" placeholder="Select Cameras" @change="queryStats">
                    <el-option
                        v-for="camera in cameras"
                        :key="camera.value"
                        :label="camera.label"
                        :value="camera.value">
                    </el-option>
                </el-select>
            </div>

            <div class="item-group">
                <label>Metric: </label>
                <el-select v-model="selectedMetric" placeholder="Select Metric" @change="queryStats">
                    <el-option
                        v-for="m in metrics"
                        :key="m.value"
                        :label="m.label"
                        :value="m.value">
                    </el-option>
                </el-select>
            </div>

            <div class="item-group">
                <label>Time Unit: </label>
                <el-select v-model="selectedTimeUnit" placeholder="Select Time Unit" @change="queryStats">
                    <el-option
                        v-for="tu in timeUnits"
                        :key="tu.value"
                        :label="tu.label"
                        :value="tu.value">
                    </el-option>
                </el-select>
            </div>

            <div class="item-group">
                <label>Date：</label>
                <el-date-picker
                    v-model="selectedDate"
                    type="daterange"
                    align="right"
                    unlink-panels
                    range-separator="To"
                    start-placeholder="Start date"
                    end-placeholder="End date"
                    :picker-options="pickerOptions"
                    @change="queryStats">
                </el-date-picker>
            </div>
        </div>

        <highcharts class="hc" :options="chartOptions" ref="chart"></highcharts>
    </div>
</div>
</template>

<script>
import Highcharts from 'highcharts'
import exportingInit from 'highcharts/modules/exporting'
import apiUtil from '@/api/api-utils'
import {formatDate} from '@/js/date'

exportingInit(Highcharts)

export default {
    name: "chart",
    props: {
        isCameraDisabled: Boolean
    },
    data () {
        return {
            // select options
            cameras: [{
                value: 'nyc_1',
                label: 'NYC Camera 1'
            }, {
                value: 'nyc_2',
                label: 'NYC Camera 2'
            }, {
                value: 'nyc_3',
                label: 'NYC Camera 3'
            }, {
                value: 'nyc_4',
                label: 'NYC Camera 4'
            }, {
                value: 'nyc_5',
                label: 'NYC Camera 5'
            }, {
                value: 'nyc_6',
                label: 'NYC Camera 6'
            }],
            metrics: [
                {
                    value: 'vehicle',
                    label: 'Vehicle'
                },
                {
                    value: 'pedestrian',
                    label: 'Pedestrian'
                }
            ],
            timeUnits: [
                {
                    value: 'half_hour',
                    label: 'Half Hour'
                },
                {
                    value: 'hour',
                    label: 'Hour'
                },
                {
                    value: 'day',
                    label: 'Day'
                },
                {
                    value: 'month',
                    label: 'Month'
                }
            ],
            selectedCameras: [],
            selectedMetric: '',
            selectedTimeUnit: '',
            selectedDate: [],
            pickerOptions: {
                shortcuts: [{
                    text: 'Last week',
                    onClick (picker) {
                        const end = new Date()
                        const start = new Date()
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
                        picker.$emit('pick', [start, end])
                    }
                }, {
                    text: 'Last month',
                    onClick (picker) {
                        const end = new Date()
                        const start = new Date()
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
                        picker.$emit('pick', [start, end])
                    }
                }, {
                    text: 'Last 3 months',
                    onClick (picker) {
                        const end = new Date()
                        const start = new Date()
                        start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
                        picker.$emit('pick', [start, end])
                    }
                }]
            },
            // chart options
            chartOptions: {
                title: {
                    text: null
                },

                yAxis: {
                    title: {
                        text: 'Amount'
                    }
                },

                xAxis: {
                    type: 'datetime'
                },

                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle'
                },

                plotOptions: {
                    series: {
                        label: {
                            connectorAllowed: false
                        }
                    }
                },

                "series": [
                ],

                responsive: {
                    rules: [{
                        condition: {
                            maxWidth: 500
                        },
                        chartOptions: {
                            legend: {
                                layout: 'horizontal',
                                align: 'center',
                                verticalAlign: 'bottom'
                            }
                        }
                    }]
                }
            }
        }
    },

    methods: {
        queryStats () {
            if (!(this.selectedCameras && this.selectedCameras.length > 0 && this.selectedMetric &&
                this.selectedTimeUnit && this.selectedDate && this.selectedDate.length === 2)) {
                return
            }

            let option = {
                camera: this.selectedCameras,
                metric: this.selectedMetric,
                'time-unit': this.selectedTimeUnit,
                start: formatDate(this.selectedDate[0], "yyyy-MM-dd"),
                end: formatDate(this.selectedDate[1], "yyyy-MM-dd")
            }

            apiUtil.getStats(this, option).then((res) => {
                this.chartOptions.series = res.body.series
            }, (err) => {
                this.$message.error(err.status)
                this.$message.error(err.body)
            })
        }
    },

    mounted () {
        this.selectedCameras = ['nyc_1']
        this.selectedMetric = 'vehicle'
        this.selectedTimeUnit = 'day'
        const end = new Date()
        const start = new Date()
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
        this.selectedDate = [start, end]

        this.queryStats()
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
