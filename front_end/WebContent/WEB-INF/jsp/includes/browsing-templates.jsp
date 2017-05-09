<!-- ----------------------------------------------------------------- -->
<div id=viewInfo_tpl class="hide">
	<span class="left">Displaying {0} to {1} of {2} <strong>{3}</strong> Results</span>
</div>

<!-- ----------------------------------------------------------------- -->
<div id="navigationBar_tpl" class="hide">
    <ul>
        <li id="{0}">
            <strong>{2}</strong>
            <span><em>{3}</em><a id="{1}" href="javascript:;" title="close"></a></span>
        </li>
    </ul>
</div>

<!-- ----------------------------------------------------------------- -->
<!-- ------------------- Target Types view templates ----------------- -->
<!-- ----------------------------------------------------------------- -->
<div id="targetTypes-mainColgroup" class="hide">
    <table>
        <colgroup>
            <col width="225" />
            <col width="100" />
            <col width="100" />
            <col width="100" />
            <col width="100" />
            <col width="105" />
        </colgroup>
    </table>
</div>

<div id="targetTypes-mainHeading" class="hide">
    <table>
        <tbody>
            <tr>
                <th>Target Type</th>
                <th>Targets #</th>
                <th>Missions #</th>
                <th>Instruments #</th>
                <th>Data Sets #</th>
                <th class="right-bg-none">Documents #</th>
            </tr>
        </tbody>
    </table>
</div>

<div id="targetTypes-innerColgroup" class="hide">
    <table>
        <colgroup>
            <col width="220" />
            <col width="100" />
            <col width="100" />
            <col width="100" />
            <col width="100" />
            <col width="100" />
        </colgroup>
    </table>
</div>

<div id="targetTypes-row" class="hide">
    <table>
        <tbody>
            <tr>
                <!-- target types column -->
                <td class="text-left"><span>{0}</span></td>

                <!--  targets column -->
                <td><a id="{1}" href="javascript:;" class="green-link">{2}</a></td>
            
                <!-- missions column  -->
                <td><a id="{3}" href="javascript:;" class="green-link">{4}</a></td>
            
                <!--  instruments column -->
                <td><a id="{5}" href="javascript:;" class="green-link">{6}</a></td>
            
                <!--  datasets column -->
                <td><a id="{7}" href="javascript:;" class="green-link">{8}</a></td>
            
                <!--  documents column -->
                <td class="right-bg-none">
                    <a id="{9}" href="javascript:;" class="green-link">{10}</a>
                </td>
        </tr>
        </tbody>
    </table>
</div>

<!-- ------------------------------------------------------------ -->
<!-- ------------------- Targets view templates ----------------- -->
<!-- ------------------------------------------------------------ -->
<div id="targets-mainColgroup" class="hide">
    <table>
        <colgroup>
            <col width="225" />
            <col width="100" />
            <col width="100" />
            <col width="100" />
            <col width="100" />
            <col width="105" />
        </colgroup>
	</table>
</div>

<div id="targets-mainHeading" class="hide">
	<table>
		<tbody>
        	<tr>
            	<th>Targets</th>
            	<th>Missions #</th>
            	<th>Instruments #</th>
            	<th>Data Sets #</th>
            	<th class="right-bg-none">Documents #</th>
            	<th></th>
        	</tr>
        </tbody>
    </table>
</div>

<div id="targets-innerColgroup" class="hide">
    <table>
        <colgroup>
            <col width="220" />
            <col width="100" />
            <col width="100" />
            <col width="100" />
            <col width="100" />
            <col width="100" />
        </colgroup>
    </table>
</div>

<div id="targets-row" class="hide">
    <table>
    	<tbody>
	        <tr>
	            <!-- targets column -->
	            <td class="text-left"><span>{0}</span></td>
	            
	            <!-- missions column  -->
	            <td><a id="{1}" href="javascript:;" class="green-link">{2}</a></td>
	            
	            <!--  instruments column -->
	            <td><a id="{3}" href="javascript:;" class="green-link">{4}</a></td>
	            
	            <!--  datasets column -->
	            <td><a id="{5}" href="javascript:;" class="green-link">{6}</a></td>
	            
	            <!--  documents column -->
	            <td class="right-bg-none">
	                <a id="{7}" href="javascript:;" class="green-link">{8}</a>
	            </td>
	            
	            <td></td>
	        </tr>
        </tbody>
    </table>
</div>

<!-- ------------------------------------------------------------ -->
<!-- ------------------- Missions view templates ----------------- -->
<!-- ------------------------------------------------------------ -->
<div id="missions-mainColgroup" class="hide">
    <table>
        <colgroup>
            <col width="225" />
            <col width="100" />
            <col width="100" />
            <col width="100" />
            <col width="205" />
        </colgroup>
	</table>
</div>

<div id="missions-mainHeading" class="hide">
	<table>
		<tbody>
        	<tr>
            	<th>Missions</th>
            	<th>Instruments #</th>
            	<th>Data Sets #</th>
            	<th class="right-bg-none">Documents #</th>
            	<th></th>
        	</tr>
        </tbody>
    </table>
</div>

<div id="missions-innerColgroup" class="hide">
    <table>
        <colgroup>
            <col width="220" />
            <col width="100" />
            <col width="100" />
            <col width="100" />
            <col width="200" />
        </colgroup>
    </table>
</div>

<div id="missions-row" class="hide">
    <table>
        <tr>
            <!-- missions column  -->
            <td><a id="{0}" href="javascript:;" class="green-link">{1}</a></td>
            
            <!--  instruments column -->
            <td><a id="{2}" href="javascript:;" class="green-link">{3}</a></td>
            
            <!--  datasets column -->
            <td><a id="{4}" href="javascript:;" class="green-link">{5}</a></td>
            
            <!--  documents column -->
            <td class="right-bg-none">
                <a id="{6}" href="javascript:;" class="green-link">{7}</a>
            </td>
            
            <td></td>
        </tr>
    </table>
</div>

<!-- ------------------------------------------------------------ -->
<!-- ------------------- Instruments view templates ----------------- -->
<!-- ------------------------------------------------------------ -->
<div id="instruments-mainColgroup" class="hide">
    <table>
        <colgroup>
            <col width="225" />
            <col width="100" />
            <col width="100" />
            <col width="305" />
        </colgroup>
	</table>
</div>

<div id="instruments-mainHeading" class="hide">
	<table>
		<tbody>
        	<tr>
            	<th>Instruments</th>
            	<th>Data Sets #</th>
            	<th class="right-bg-none">Documents #</th>
            	<th></th>
        	</tr>
        </tbody>
    </table>
</div>

<div id="instruments-innerColgroup" class="hide">
    <table>
        <colgroup>
            <col width="220" />
            <col width="100" />
            <col width="100" />
            <col width="300" />
        </colgroup>
    </table>
</div>

<div id="instruments-row" class="hide">
    <table>
        <tr>
            <!--  instruments column -->
            <td><a id="{0}" href="javascript:;" class="green-link">{1}</a></td>
            
            <!--  datasets column -->
            <td><a id="{2}" href="javascript:;" class="green-link">{3}</a></td>
            
            <!--  documents column -->
            <td class="right-bg-none">
                <a id="{4}" href="javascript:;" class="green-link">{5}</a>
            </td>
            
            <td></td>
        </tr>
    </table>
</div>

<!-- ------------------------------------------------------------ -->
<!-- ------------------- DataSets view templates ----------------- -->
<!-- ------------------------------------------------------------ -->
<div id="datasets-mainColgroup" class="hide">
    <table>
        <colgroup>
            <col width="225" />
            <col width="100" />
            <col width="405" />
        </colgroup>
	</table>
</div>

<div id="datasets-mainHeading" class="hide">
	<table>
		<tbody>
        	<tr>
            	<th>Data Sets</th>
            	<th class="right-bg-none">Documents #</th>
            	<th></th>
        	</tr>
        </tbody>
    </table>
</div>

<div id="datasets-innerColgroup" class="hide">
    <table>
        <colgroup>
            <col width="220" />
            <col width="100" />
            <col width="400" />
        </colgroup>
    </table>
</div>

<div id="datasets-row" class="hide">
    <table>
        <tr>
            <!--  datasets column -->
            <td><a id="{0}" href="javascript:;" class="green-link">{1}</a></td>
            
            <!--  documents column -->
            <td class="right-bg-none">
                <a id="{2}" href="javascript:;" class="green-link">{3}</a>
            </td>
            
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </table>
</div>

<!-- ------------------------------------------------------------ -->
<!-- ------------------- Documents view templates ----------------- -->
<!-- ------------------------------------------------------------ -->
<div id="documents-mainColgroup" class="hide">
    <table>
        <colgroup>
            <col width="730" />
        </colgroup>
	</table>
</div>

<div id="documents-mainHeading" class="hide">
	<table>
		<tbody>
        	<tr>
            	<th class="right-bg-none">Documents</th>
        	</tr>
        </tbody>
    </table>
</div>

<div id="documents-innerColgroup" class="hide">
    <table>
        <colgroup>
            <col width="720" />
        </colgroup>
    </table>
</div>

<div id="documents-row" class="hide">
    <table>
        <tr>
            <td class="right-bg-none">
                <a id="{0}" href="javascript:;" class="green-link">{1}</a>
            </td>
        </tr>
    </table>
</div>
