<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/china.js"></script>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="statistics_china" style="width: 100%;height: 100%;margin-top: 30px;margin-left: 30px">

</div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('statistics_china'));

    /*function randomData() {
        return Math.round(Math.random() * 1000);


    }*/

    option = {
        title: {
            text: '持名法州APP用户分布图',
            subtext: '2019年4月21日 最新数据',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        // 说明
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['男', '女']
        },
        visualMap: {
            min: 0,
            max: 2500,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        // 工具箱
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        series: [
            {
                name: '男',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: []
            },
            {
                name: '女',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: []
            }
        ]
    };
    myChart.setOption(option);

    $(function () {
        $.post("${pageContext.request.contextPath }/statistics/distribution?sex=1", function (data) {
            console.log(data);
            myChart.setOption({
                series: [{
                    // 根据名字对应到相应的系列
                    name: '男',
                    data: data.china
                }]
            });
        }, "json");


        $.post("${pageContext.request.contextPath }/statistics/distribution?sex=0", function (data) {
            console.log(data);
            myChart.setOption({
                series: [{
                    // 根据名字对应到相应的系列
                    name: '女',
                    data: data.china
                }]
            });
        }, "json");
    })
</script>


<%--
mvn install:install-file -Dfile=F:\java\api_sdk\aliyun-java-sdk-core\aliyun-java-sdk-core-3.3.1.jar -DgroupId=com.baizhi -DartifactId=aaa -Dversion=1.0 -Dpackaging=jar



mvn install:install-file -Dfile=F:\java\api_sdk\aliyun-java-sdk-dysmsapi\aliyun-java-sdk-dysmsapi-1.0.0.jar -DgroupId=com.baizhi -DartifactId=bbb -Dversion=1.0 -Dpackaging=jar
--%>
















