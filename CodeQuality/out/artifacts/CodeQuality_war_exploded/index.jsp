<%--
  Created by IntelliJ IDEA.
  User: sachin
  Date: 2019-08-05
  Time: 19.15SSSS
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SPM</title>
    <link rel="stylesheet" type="text/css" href="CSS/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="CSS/dataTables.bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="CSS/fixedHeader.dataTables.min.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.dataTables.min.js"></script>
    <script src="js/dataTables.buttons.min.js"></script>
    <script src="js/buttons.flash.min.js"></script>
    <script src="js/jszip.min.js"></script>
    <script src="js/pdfmake.min.js"></script>
    <script src="js/vfs_fonts.js"></script>
    <script src="js/buttons.html5.min.js"></script>
    <script src="js/buttons.print.min.js"></script>
    <script src="js/Chart.js"></script>
  <script src="js/dataTables.fixedHeader.min.js"></script>


</head>
<body class="p-3 mb-2 bg-dark text-white">
<h1>SPM </h1>
<div align="center" width="100%">
    <label for="fileUploader">Select a file to upload:</label>
    <input type="file" name="file" size="50" id="fileUploader" class="btn btn-primary"/>
</div>
<br/><br>
<div id="dis"></div>
<table id="example" class="table table-bordered table-hover" style="width:100%">
    <thead>
    <tr class="bg-success">
        <td width="3%">Line no</td>
        <td width="46%">Program statements</td>
        <td width="30%">Tokens identified under the size factor</td>
        <td width="3%">Cs</td>
        <td width="3%">Ctc</td>
        <td width="3%">Cnc</td>
        <td width="3%">Ci</td>
        <td width="3%">TW</td>
        <td width="3%">Cps</td>
        <td width="3%">Cr</td>

    </tr>
    </thead>
    <tbody>

    </tbody>
    <tfoot>
    <tr class="bg-info">
        <td width="3%">Line no</td>
        <td width="46%">Program statements</td>
        <td width="30%">Tokens identified under the size factor</td>
        <td width="3%">Cs</td>
        <td width="3%">Ctc</td>
        <td width="3%">Cnc</td>
        <td width="3%">Ci</td>
        <td width="3%">TW</td>
        <td width="3%">Cps</td>
        <td width="3%">Cr</td>

    </tr>
    </tfoot>

</table>


<div width="100%" style="height: 700px;">
    <canvas id="chart-0"></canvas>
</div>

<script>

    $('#fileUploader').on('change', uploadFile)

    function uploadFile(event) {
        event.stopPropagation();
        event.preventDefault();
        var files = event.target.files;
        var data = new FormData();
        $.each(files, function (key, value) {
            data.append(key, value);
        });
        loadetable(data);
    }

    $(document).ready(function () {
      document.title='Code Quality';
        $('#example').dataTable({
          "dom": '<"dt-buttons"Bfli>rtp',
            buttons: [
              'csv', 'excel', 'pdf', 'print'
            ],
            "paging": false,
            "fixedHeader": true,
            "searching": false,
            "ordering": false

        });
    });

    function loadetable(data) {
        document.getElementById("chart-0").innerHTML = "";
        let nestchart = new Array();
        let sizecountchart = new Array();
        let lines = new Array();
        let inhechart=new Array();
        let ctcchart=new Array();
        let twchart=new Array();
        let cpschart=new Array();
        let crchart=new Array();
        let cp=0;
        $.ajax({
            url: 'ServletFileUploard',
            type: 'POST',
            data: data,
            cache: false,
            dataType: 'json',
            processData: false,
            contentType: false,
            success: function (data, textStatus, jqXHR) {
                // console.log(data);


                $("#example > tbody").html("");

                for (var i = 0; i < data.length; i++) {


                    lines.push('L:' + (i + 1));
                    if (data[i].nextcount == "") {
                        nestchart.push(0);
                    } else {
                        nestchart.push(parseInt(data[i].nextcount, 10));
                    }

                    if (data[i].inheritcount == "") {
                        inhechart.push(0);
                    } else {
                        inhechart.push(parseInt(data[i].inheritcount, 10));
                    }
                    if (data[i].ctc == "") {
                        ctcchart.push(0);
                    } else {
                        ctcchart.push(parseInt(data[i].ctc, 10));
                    }

                    let si="";
                    if (data[i].sizecount != 0) {
                        si=data[i].sizecount;
                    }


                    sizecountchart.push(data[i].sizecount);



                    let tw=ctcchart[ctcchart.length-1]+nestchart[nestchart.length-1]+inhechart[inhechart.length-1];
                    twchart.push(tw);

                    let cps =tw*sizecountchart[sizecountchart.length-1];
                    cpschart.push(cps);

                    let cr=0;

                    if (data[i].re == false) {
                        cr=cps*2;
                        crchart.push(cr);
                    }else{
                        cr="";
                        crchart.push(0);
                    }

                    cp=cp+cps+cr;
                    if(cps==0){
                        cps="";
                    }
                    if(tw==0){
                        tw="";
                    }
                    if(cr==0){
                        cr="";
                    }


                    var tab = '';
                    tab += "<tr class='table-info text-dark'>";
                    tab += "<td>";
                    tab += i + 1;
                    tab += "</td>"
                    tab += "<td>";
                    tab += data[i].code;
                    tab += "</td>";
                    tab += "<td>";
                    tab += data[i].sizeall;
                    tab += "</td>";
                    tab += "<td>";
                    tab += si;
                    tab += "</td>";
                    tab += "<td>";
                    tab += data[i].ctc;
                    tab += "</td>";
                    tab += "<td>";
                    tab += data[i].nextcount;
                    tab += "</td>";
                    tab += "<td>";
                    tab += data[i].inheritcount;
                    tab += "</td>";
                    tab += "<td>";
                    tab += tw;
                    tab += "</td>";
                    tab += "<td>";
                    tab += cps;
                    tab += "</td>";
                    tab += "<td>";
                    tab += cr;
                    tab += "</td>";
                    tab += "</tr>";
                    $('#example > tbody').append(tab);
                }

                let tabtotal="";
                tabtotal+="<tr  class='table-danger text-dark'>";
                tabtotal+="<td align='center' colspan='3'>";
                tabtotal+="Cp";
                tabtotal+="</td>";
                tabtotal+="<td align='center' colspan='7'>";
                tabtotal+=cp;
                tabtotal+="</td>";
                tabtotal+="</tr>";
                $('#example > tbody').append(tabtotal);

                var options = {
                    maintainAspectRatio: false,
                    spanGaps: false,
                    elements: {
                        line: {
                            tension: 0.000001
                        }
                    },
                    plugins: {
                        filler: {
                            propagate: false
                        }
                    },
                    scales: {
                        xAxes: [{
                            ticks: {
                                autoSkip: false,
                                maxRotation: 0
                            }
                        }]
                    }
                };


                document.getElementById("chart-0").innerHTML = "";
                console.log(lines.length);
                console.log(lines);
                console.log(nestchart.length);

                console.log(nestchart);

                new Chart('chart-0', {
                    type: 'line',
                    data: {
                        labels: lines,
                        datasets: [{
                            backgroundColor: '#FFA5B4',
                            borderColor: '#FB052E',
                            data: nestchart,
                            label: 'CNC',
                            fill: false
                        }, {
                            backgroundColor: '#81F781',
                            borderColor: '#01DF01',
                            data: sizecountchart,
                            label: 'Cs',
                            fill: false
                        },{
                            backgroundColor: '#FAFD7E',
                            borderColor: '#F7FE01',
                            data: inhechart,
                            label: 'Ci',
                            fill: false
                        },{
                            backgroundColor: '#968DFD',
                            borderColor: '#1804FB',
                            data: ctcchart,
                            label: 'Ctc',
                            fill: false
                        },{
                            backgroundColor: '#FBB3D6',
                            borderColor: '#FB037B',
                            data: twchart,
                            label: 'tw',
                            fill: false
                        },{
                            backgroundColor: '#FEC892',
                            borderColor: '#F77C02',
                            data: cpschart,
                            label: 'cps',
                            fill: false
                        },{
                            backgroundColor: '#96FCE6',
                            borderColor: '#03FBC5',
                            data: crchart,
                            label: 'cr',
                            fill: false
                        }]
                    },
                    options: Chart.helpers.merge(options, {
                        title: {
                            text: 'Chart: ',
                            display: true
                        }
                    })
                });


            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log('ERRORS: ' + textStatus);
                console.log(errorThrown);
                console.log(jqXHR);
            }
        });



    }


</script>

</body>
</html>
