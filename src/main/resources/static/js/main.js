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
		$('#btnmetainsert').on('click', function () {
            _this.minsert();
        });
        $('#btnmemberlogin').on('click', function () {
            _this.mlogin();
        });
        $('#btnmemberupdate').on('click', function () {
            _this.mupdate();
        });
        $('#btnmetaupdate').on('click', function () {
            _this.metaupdate();
        });
        $('#btnboardupdate').on('click', function () {
            _this.boardupdate();
        });
        $('#btnboardinsert').on('click', function () {
            _this.boardinsert();
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
			location.href="./";
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
			location.href="./metasearch";
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
				location.href="./main";
			}else{
				alert("login fail");
				location.href="./";
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
			id:$('#id').val(),
			password:$('#password').val(),
			name:$('#name').val(),
			team:$('#team').val()
			}
		console.log(data);
		var url="/updateMember";
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function(data) {
        	console.log("mupdate complete");
            //location.reload();
            
            if(data==="true"){
				alert("개인정보가 수정되었습니다. 다시 로그인해주세요.");
				location.href="./";
			}else{
				alert("개인정보 수정 실패. 다시 시도해주세요");
				location.href="main";
			}
        })
        .fail(function (error) {
        	console.log("mupdate fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },
    metaupdate : function () {
    	console.log("meta update activated");
		var data={
			metaSeq:$('#metaSeq').val(),
			nameEng:$('#nameEng').val(),
			nameKor:$('#nameKor').val(),
			explanation:$('#explanation').val(),
			type:$('#type').val()
			}
		console.log(data);
		var url="/metaUpdate";
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function(data) {
        	console.log("meta update complete");
            if(data==="success"){
				alert("Update!");
				location.reload();
			}else{
				alert("Update fail");
				location.href="./";
			}
        })
        .fail(function (error) {
        	console.log("meta update fail");
        	console.log(JSON.stringify(error));
			alert("something wrong... contact -> 01024299420")
        });
    },
    boardinsert : function () {
    	console.log("board insert activated");
		var data={
			title:$('#title').val(),
			content:$('#content').val()
			}
		console.log(data);
		var url="/insertBoard";
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function() {
        	console.log("board insert complete");
            location.href="boardlist";
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
			title:$('#title').val(),
			content:$('#content').val()
			}
		console.log(data);
		var url="/updateBoard";
		$.ajax({
            type:'POST',
            url:url,
            dataType:"text",
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data),
        })
        .done(function(data) {
        	console.log("board update complete");
            if(data==="success"){
				alert("Update!");
				location.href="boardlist";
			}else{
				alert("Update fail");
				location.href="boardlist";
			}
        })
        .fail(function (error) {
        	console.log("board update fail");
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
			location.href="infoBoard?id="+data.boardId;
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
    }
};


main.init();
