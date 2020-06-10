<template>
<div class="list">
    <div>
        <div class="title-section">
            <div class="item-group">
                <label>Selected Cameras：</label>
                <el-select v-model="selectedCameras" multiple filterable placeholder="Select Cameras">
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
                <el-select v-model="selectedMetric" placeholder="Select Metric">
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
                <el-select v-model="selectedTimeUnit" placeholder="Select Time Unit">
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
                    :picker-options="pickerOptions">
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

exportingInit(Highcharts)

export default {
    data () {
        return {
            // select options
            cameras: [{
                value: 'camera_1',
                label: 'Camera1'
            }, {
                value: 'camera_2',
                label: 'Camera2'
            }, {
                value: 'camera_3',
                label: 'Camera3'
            }, {
                value: 'camera_4',
                label: 'Camera4'
            }, {
                value: 'camera_5',
                label: 'Camera5'
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
                    value: 'week',
                    label: 'Week'
                },
                {
                    value: 'month',
                    label: 'Month'
                }
            ],
            selectedCameras: [],
            selectedMetric: '',
            selectedTimeUnit: '',
            selectedDate: '',
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
                    // accessibility: {
                    //     rangeDescription: 'Range: 2010 to 2017'
                    // }
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
                        },
                        pointStart: 2010
                    }
                },

                series: [{
                    name: 'Installation',
                    data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
                }, {
                    name: 'Manufacturing',
                    data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
                }, {
                    name: 'Sales & Distribution',
                    data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
                }, {
                    name: 'Project Development',
                    data: [null, null, 98, 213, 15112, 234, 34400, 54]
                }, {
                    name: 'Project Development2',
                    data: [null, null, 123, 12169, 45, 22452, 45, 34227]
                }, {
                    name: 'Project Development3',
                    data: [null, null, 678, 123, 15112, 22452, 123, 675]
                }, {
                    name: 'Other',
                    data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111]
                }],

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
