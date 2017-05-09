<div id="searchResultTemplate" class="hide">
	<ul>
		<li style="padding-left:15px;">
			<a id="{0}" href="javascript:;" class="green-link">{1} ({2})</a>
		</li>
	</ul>
</div>

<div id="advancedSearchRowTemplate" class="hide">
	<table>
		<tr>
			<td><input name="" type="checkbox" value="" checked="checked" /></td>

			<td>
				<select name="" class="parameter">
					<option value="TT">Target Types</option>
					<option selected="selected" value="T">Targets</option>
					<option value="M">Missions</option>
					<option value="IH">Instrument Hosts</option>
					<option value="I">Instruments</option>
					<option value="DS_NAME">Data Set Name</option>
					<option value="DS_ID">Data Set ID</option>
					<option value="START_DATE">Start Date</option>
					<option value="STOP_DATE">Stop Date</option>
				</select>                          
			</td>

			<td><input name="" type="text" class="blue-reset values" /></td>

			<td>
				<select name="" class="condition">
					<option selected="selected" value="and ">and</option>
					<option value="or">or</option>
				</select>
			</td>

			<td class="right-bg-none">
				<a id="addMore" href="javascript:;" class="add-more-btn right-spacing">
					<span class="left-button">
					<span class="right-button">
					<span class="itext">Add more</span></span></span>
				</a>
			</td>
		</tr>
	</table>
</div>
