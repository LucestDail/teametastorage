/**
 * 
 */


main = {
    init : function () {
    	console.log("init activated");
        var _this = this;
        $('#btnmetasearch').on('click', function () {
            _this.msearch();
        });
		$('#btnmemberregister').on('click', function () {
            _this.mregister();
        });
		
        $('#btnmemberlogin').on('click', function () {
            _this.mlogin();
        });
        $('#btnmemberupdate').on('click', function () {
            _this.mupdate();
		});
		$('#btnmemberdelete').on('click', function () {
            _this.mdelete();
		});
		$('#btnmetainsert').on('click', function () {
            _this.metainsert();
        });
        $('#btnmetaupdate').on('click', function () {
            _this.metaupdate();
		});
		$('#btnmetadelete').on('click', function () {
            _this.metadelete();
        });
        $('#btnboardupdate').on('click', function () {
            _this.boardupdate();
        });
        $('#btnboardinsert').on('click', function () {
            _this.boardinsert();
        });
        $('#btnboarddelete').on('click', function () {
            _this.boarddelete();
        });
        $('#btncommentinsert').on('click', function () {
            _this.commentinsert();
        });
        $('#btncommentdelete').on('click', function () {
            _this.commentdelete();
        });
        $('#btnteamvalidate').on('click', function () {
            _this.teamvalidate();
        });
        $('#btnidvalidate').on('click', function () {
            _this.idvalidate();
        });
        $('#btnpolicyinsert').on('click', function () {
            _this.policyinsert();
		});
		$('#btnnoticeinsert').on('click', function () {
            _this.noticeinsert();
		});
		$('#btnserviceinsert').on('click', function () {
            _this.serviceinsert();
		});
		$('#btntechinsert').on('click', function () {
            _this.techinsert();
		});
		$('#btnusuallyinsert').on('click', function () {
            _this.usuallyinsert();
		});
		$('#btnteamupdate').on('click', function () {
            _this.teamupdate();
		});
		$('#btnteamnoticeinsert').on('click', function () {
            _this.teamnoticeinsert();
		});
		$('#btnteamnoticeupdate').on('click', function () {
            _this.teamnoticeupdate();
		});
		$('#btnteamnoticedelete').on('click', function () {
            _this.teamnoticedelete();
		});
		$('#btnqnainsert').on('click', function () {
            _this.qnainsert();
		});
		$('#btnqnaupdate').on('click', function () {
            _this.qnaupdate();
		});
		$('#btnqnadelete').on('click', function () {
            _this.qnadelete();
		});
		$('#btnworkinsert').on('click', function () {
            _this.workinsert();
		});
		$('#btnworkupdate').on('click', function () {
            _this.workupdate();
		});
		$('#btnworkdelete').on('click', function () {
            _this.workdelete();
		});
    },
    msearch : function () {
    	console.log("meta-search activated");
		var data="id="+$('#searchtarget').val()
		//location.href=url;
		var url="/metaSearchList";
		$.ajax({
            type: 'GET',
            url:url,
            dataType: "text",
            contentType:'application/json; charset=utf-8',
            data:data,
        })
        .done(function() {
        	console.log("msearch complete");
			url += "?"+data;
			location.href=url;
        })
        .fail(function (error) {
        	console.log("msearch fail");
        	console.log(JSON.stringify(error));
			location.href="/failurl?"+data;
        });
    },
    mregister : function () {
    	console.log("member-register activated");
		var data={
			id:$('#id').val(),
			password:$('#password').val(),
			name:$('#name').val(),
			team:$('#team').val()
			}
		console.log(data);
		//location.href=url;
		var url="/createMember";
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function() {
        	console.log("mregister complete");
            //location.reload();
			alert("Welcome!");
			location.href="/";
        })
        .fail(function (error) {
        	console.log("mregister fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },
    minsert : function () {
    	console.log("meta-insert activated");
		var data={
			id:$('#id').val(),
			nameEng:$('#name_eng').val(),
			nameKor:$('#name_kor').val(),
			explanation:$('#explanation').val(),
			type:$('#type').val()
			}
		console.log(data);
		//location.href=url;
		var url="/insertMeta";
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function() {
        	console.log("minsert complete");
            //location.reload();
			alert("register meta data!");
			location.href="/metasearch";
        })
        .fail(function (error) {
        	console.log("register meta data fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },
    mlogin : function () {
    	console.log("member-login activated");
		var data={
			id:$('#id').val(),
			password:$('#password').val()
			}
		console.log(data);
		//location.href=url;
		var url="/loginMember";
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function(data) {
        	console.log("mlogin complete");
            //location.reload();
			console.log(data);
			if(data==="true"){
				alert("Welcome!");
				location.href="/main";
			}else{
				alert("login fail");
				location.href="/";
			}
        })
        .fail(function (error) {
        	console.log("login fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },
    mupdate : function () {
    	console.log("member update activated");
		var data={
			memberSeq:$('#seq').val(),
			id:$('#id').val(),
			password:$('#updatePassword').val(),
			name:$('#updateName').val(),
			team:$('#updateTeam').val(),
			info:$('#updateInfo').val()
			}
		console.log(data);
		var seq = $('#seq').val();
		var url="/member/detail/"+seq;
		console.log(url);
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function(data) {
        	console.log("mupdate complete");
            if(data==="true"){
				alert("개인정보가 수정되었습니다. 다시 로그인해주세요.");
				location.href="/";
			}else{
				alert("개인정보 수정 실패. 다시 시도해주세요");
				location.href=url;
			}
        })
        .fail(function (error) {
        	console.log("mupdate fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
	},
	mdelete : function () {
		console.log("member delete activated");
		var data={
			}
		var seq = $('#seq').val();
		var url="/member/detail/"+seq;
		console.log(url);
		$.ajax({
            type:'DELETE',
            url:url,
			dataType:"text",
			contentType:'application/json; charset=utf-8',
            data:data,
        })
        .done(function() {
			console.log("member delete complete");
			location.href="/";
        })
        .fail(function (error) {
        	console.log("fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },
    boardinsert : function () {
    	console.log("board insert activated");
		var data={
			title:$('#title').val(),
			content:editor.getHTML(),
			category:$('#category').val()
			}
		var currentBoard = $('#category').val();
		console.log(data);
		var url="/board/"+currentBoard;
		$.ajax({
            type:'PUT',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function() {
        	console.log("board insert complete");
            location.href=url;
        })
        .fail(function (error) {
        	console.log("board insert fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },
    boardupdate : function () {
    	console.log("board update activated");
		var data={
			boardSeq:$('#boardSeq').val(),
			title:$('#updateTitle').val(),
			content:editor.getHTML()
			}
		var currentBoard = $('#category').val();
		var currentBoardSeq = $('#seq').val();
		console.log(data);
		var url="/board/"+currentBoard + "/" + currentBoardSeq;
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function(data) {
        	console.log("board update complete");
            if(data==="true"){
				alert("Update!");
				location.href=url;
			}else{
				alert("Update fail");
				location.href=url;
			}
        })
        .fail(function (error) {
        	console.log("board update fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },
    boarddelete : function () {
		console.log("board delete activated");
		var data={
			}
		var currentBoard = $('#category').val();
		var currentBoardSeq = $('#seq').val();
		console.log(data);
		var url="/board/"+currentBoard + "/" + currentBoardSeq;
		console.log(data + url);
		$.ajax({
            type:'DELETE',
            url:url,
			dataType:"text",
			contentType:'application/json; charset=utf-8',
            data:data,
        })
        .done(function() {
			console.log("teamnotice delete complete");
			location.href="/board/"+currentBoard;
        })
        .fail(function (error) {
        	console.log("fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },
    commentinsert : function () {
    	console.log("comment insert activated");
		var data={
			boardId:$('#boardSeq').val(),
			content:$('#content').val()
			}
		var currentBoard = $('#category').val();
		var currentBoardSeq = $('#seq').val();
		console.log(data);
		var url="/insertComment";
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function() {
        	console.log("comment insert complete");
			location.href="/board/"+currentBoard + "/" + currentBoardSeq;
        })
        .fail(function (error) {
        	console.log("comment insert fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },
    idvalidate : function () {
    	console.log("id validate activated");
		var data={
			id:$('#id').val()
			}
		console.log(data);
		var url="/validateId";
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function(data) {
        	if(data === 'true'){
        		alert("ID 사용 가능합니다");
        		document.getElementById('btnmemberregister').disabled = false;
				document.getElementById('id').setAttribute("readonly", true);
        	}else{
        		alert("ID가 이미 있습니다");
        	}
        })
        .fail(function (error) {
        	console.log("id validate fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },
    teamvalidate : function () {
    	console.log("team validate activated");
		var data={
			team:$('#team').val()
			}
		console.log(data);
		var url="/validateTeam";
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function(data) {
        	if(data === 'true'){
        		alert("해당 팀은 없습니다. 가입할 경우 해당 팀을 새로 생성합니다");
        	}else{
        		alert("해당 팀은 이미 있습니다. 가입할 경우 해당 팀장에게 가입 요청을 발송합니다");
        	}
        })
        .fail(function (error) {
        	console.log("team validate fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },
    policyinsert : function () {
    	console.log("policy insert activated");
		var data={
			title:$('#title').val(),
			content:$('#content').val()
			}
		console.log(data);
		var url="/qna/putpolicy";
		$.ajax({
            type:'PUT',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function() {
			console.log("policy insert complete");
			location.href="/qna/policy";
        })
        .fail(function (error) {
        	console.log("policy insert got problem");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
			location.href="/qna/policy";
        });
    },
    noticeinsert : function () {
    	console.log("notice insert activated");
		var data={
			title:$('#title').val(),
			content:$('#content').val()
			}
		console.log(data);
		var url="/qna/putnotice";
		$.ajax({
            type:'PUT',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function() {
			console.log("policy insert complete");
			location.href="/qna/notice";
        })
        .fail(function (error) {
        	console.log("policy insert got problem");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
			location.href="/qna/notice";
        });
    },
    serviceinsert : function () {
    	console.log("service insert activated");
		var data={
			title:$('#title').val(),
			content:$('#content').val()
			}
		console.log(data);
		var url="/qna/putservice";
		$.ajax({
            type:'PUT',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function() {
			console.log("policy insert complete");
			location.href="/qna/service";
        })
        .fail(function (error) {
        	console.log("policy insert got problem");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
			location.href="/qna/service";
        });
    },
    techinsert : function () {
    	console.log("tech insert activated");
		var data={
			title:$('#title').val(),
			content:$('#content').val()
			}
		console.log(data);
		var url="/qna/puttech";
		$.ajax({
            type:'PUT',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function() {
			console.log("policy insert complete");
			location.href="/qna/tech";
        })
        .fail(function (error) {
        	console.log("policy insert got problem");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
			location.href="/qna/tech";
        });
    },
	usuallyinsert : function () {
    	console.log("usually insert activated");
		var data={
			title:$('#title').val(),
			content:$('#content').val()
			}
		console.log(data);
		var url="/qna/putusually";
		$.ajax({
            type:'PUT',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function() {
			console.log("policy insert complete");
			location.href="/qna/usually";
        })
        .fail(function (error) {
        	console.log("policy insert got problem");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
			location.href="/qna/usually";
        });
    },
    teamupdate : function () {
    	console.log("team update activated");
		var data={
			teamDetailSeq:$('#teamDetailSeq').val(),
			team:$('#teamId').val(),
			description:$('#description').val()
			}
		console.log(data);
		var url="/team/teamdetail/"+data.team;
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function(data) {
        	console.log("team update complete");
            if(data==="success"){
				alert("Update!");
				location.href=url;
			}else{
				alert("Update fail");
				location.href=url;
			}
        })
        .fail(function (error) {
        	console.log("fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },teamnoticeinsert : function () {
    	console.log("teamnotice insert activated");
		var data={
			title:$('#title').val(),
			content:$('#content').val()
			}
		var team = $('#team').val();
		console.log(data);
		var url="/team/teamnotice/"+team;
		$.ajax({
            type:'PUT',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function(data) {
        	console.log("teamnotice insert complete");
            if(data==="true"){
				alert("Insert!");
				location.href=url;
			}else{
				alert("Insert fail");
				location.href=url;
			}
        })
        .fail(function (error) {
        	console.log("fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },teamnoticeupdate : function () {
    	console.log("teamnotice update activated");
		var data={
			teamNoticeSeq:$('#teamNoticeSeq').val(),
			title:$('#updateTitle').val(),
			content:$('#updateContent').val()
			}
		console.log(data);
		var noticeSeq = $('#teamNoticeSeq').val();
		var team = $('#team').val();
		var url="/team/teamnotice/"+team+"/"+data.teamNoticeSeq;
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function(data) {
        	console.log("board update complete");
            if(data==="true"){
				alert("Update!");
				location.href=url;
			}else{
				alert("Update fail");
				location.href=url;
			}
        })
        .fail(function (error) {
        	console.log("fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },teamnoticedelete : function () {
		console.log("teamnotice delete activated");
		var data={
			teamNoticeSeq:$('#teamNoticeSeq').val(),
			}
		var teamNoticeSeq = $('#teamNoticeSeq').val();
		var team = $('#team').val();
		var url="/team/teamnotice/"+team+"/"+teamNoticeSeq;
		console.log(data + url);
		$.ajax({
            type:'DELETE',
            url:url,
			dataType:"text",
			contentType:'application/json; charset=utf-8',
            data:data,
        })
        .done(function() {
			console.log("teamnotice delete complete");
			location.href="/team/teamnotice/"+team;
        })
        .fail(function (error) {
        	console.log("fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },qnainsert : function () {
    	console.log("qna insert activated");
		var data={
			title:$('#title').val(),
			content:$('#content').val()
			}
		var category = $('#category').val();
		
		var url="/qna/"+category;
		console.log(data + url);
		$.ajax({
            type:'PUT',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function(data) {
        	console.log("qna insert complete");
            if(data==="true"){
				alert("Insert!");
				location.href=url;
			}else{
				alert("Insert fail");
				location.href=url;
			}
        })
        .fail(function (error) {
        	console.log("fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },qnaupdate : function () {
    	console.log("qna update activated");
		var data={
			qnaSeq:$('#seq').val(),
			title:$('#updateTitle').val(),
			content:$('#updateContent').val()
			}
		console.log(data);
		var seq = $('#seq').val();
		var category = $('#category').val();
		var url="/qna/"+category+"/"+seq;
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function(data) {
        	console.log("qna update complete");
            if(data==="true"){
				alert("Update!");
				location.href=url;
			}else{
				alert("Update fail");
				location.href=url;
			}
        })
        .fail(function (error) {
        	console.log("fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },qnadelete : function () {
		console.log("qna delete activated");
		var data={
			}
		var seq = $('#seq').val();
		var category = $('#category').val();
		var url="/qna/"+category+"/"+seq;
		console.log(data + url);
		$.ajax({
            type:'DELETE',
            url:url,
			dataType:"text",
			contentType:'application/json; charset=utf-8',
            data:data,
        })
        .done(function() {
			console.log("teamnotice delete complete");
			location.href="/qna/"+category;
        })
        .fail(function (error) {
        	console.log("fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },metainsert : function () {
    	console.log("meta insert activated");
		var data={
			title:$('#title').val(),
			description:editor.getHTML()
			}
		var team = $('#team').val();
		var url="/meta/"+team;
		console.log(url);
		$.ajax({
            type:'PUT',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function() {
			console.log("meta insert complete");
			alert("작성하였습니다");
				location.href=url;
        })
        .fail(function (error) {
        	console.log("fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },metaupdate : function () {
    	console.log("meta update activated");
		var data={
			title:$('#updateTitle').val(),
			description:editor.getHTML()
			}
		var seq = $('#seq').val();
		var team = $('#team').val();
		var url="/meta/"+team+"/"+seq;
		console.log(url);
		console.log(data);
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function() {
			console.log("meta update complete");
			alert("Update!");
			location.href=url;
        })
        .fail(function (error) {
        	console.log("fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },metadelete : function () {
		console.log("meta delete activated");
		var data={
			}
		var seq = $('#seq').val();
		var team = $('#team').val();
		var url="/meta/"+team+"/"+seq;
		console.log(url);
		$.ajax({
            type:'DELETE',
            url:url,
			dataType:"text",
			contentType:'application/json; charset=utf-8',
            data:data,
        })
        .done(function() {
			console.log("meta delete complete");
			location.href="/meta/"+team;
        })
        .fail(function (error) {
        	console.log("fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },workinsert : function () {
    	console.log("work insert activated");
		var data={
			title:$('#title').val(),
			description:editor.getHTML(),
			metalist:$('#metalist').val(),
			start:$('#start').val(),
			finish:$('#finish').val()
			}
		var team = $('#team').val();
		var url="/work/"+team;
		console.log(url);
		$.ajax({
            type:'PUT',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function() {
			console.log("meta insert complete");
			alert("작성하였습니다");
				location.href=url;
        })
        .fail(function (error) {
        	console.log("fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },workupdate : function () {
    	console.log("work update activated");
		var data={
			title:$('#updateTitle').val(),
			description:editor.getHTML(),
			metalist:$('#metalist').val(),
			start:$('#updateStart').val(),
			finish:$('#updateFinish').val()
			}
		var seq = $('#seq').val();
		var team = $('#team').val();
		var url="/work/"+team+"/"+seq;
		console.log(url);
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function() {
			console.log("work update complete");
			alert("Update!");
			location.href=url;
        })
        .fail(function (error) {
        	console.log("fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },workdelete : function () {
		console.log("work delete activated");
		var data={
			}
		var seq = $('#seq').val();
		var team = $('#team').val();
		var url="/work/"+team+"/"+seq;
		console.log(url);
		$.ajax({
            type:'DELETE',
            url:url,
			dataType:"text",
			contentType:'application/json; charset=utf-8',
            data:data,
        })
        .done(function() {
			console.log("work delete complete");
			location.href="/work/"+team;
        })
        .fail(function (error) {
        	console.log("fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },
};


main.init();
