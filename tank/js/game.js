var KEY={
	UP:38,
	DOWN:40,
	W:87,
	S:83,
	D:68,
	A:65,
	SPACE:32,
};
var MAX_BOX=20;
var tankGame={};
tankGame.playground={
	width:parseInt($('.playground').css('width')),
	height:parseInt($('.playground').css('height')),
};
tankGame.box={
	width:parseInt($('.box').css('width')),
	height:parseInt($('.box').css('height')),
};
tankGame.myTank={
	width:parseInt($('#me').css('width')),
	height:parseInt($('#me').css('height')),
	top:parseInt($('#me').css('top')),
	left:parseInt($('#me').css('left')),
	direction:KEY.D,
	alive:false,
	speed:5,	//if this changed, box crash may not correct
	shootRange:300,
};
tankGame.myBullet={
	width:parseInt($('#myBullet').css('width')),
	height:parseInt($('#myBullet').css('height')),
	top:parseInt($('#myBullet').css('top')),
	left:parseInt($('#myBullet').css('left')),
	direction:KEY.D,
	alive:false,
	speed:5,	//if this changed, box crash may not correct
	startLeft:-9,
	startTop:-9,
};
tankGame.pressedKeys=[];
tankGame.boxSet=[];
tankGame.otherTank=[];
tankGame.otherBullet=[]

function box(left,top,width,height,solid){
	this.left=left;
	this.top=top;
	this.width=width;
	this.height=height;
	this.solid=solid;
}

$(function(){
	tankGame.timer=setInterval(gameLoop,30);
	tankGame.timer=setInterval(gameLoopShort,10);
	$(document).keydown(function(e){
		tankGame.pressedKeys[e.which]=true;
	});
	$(document).keyup(function(e){
		tankGame.pressedKeys[e.which]=false;
	});
	
	//initialize boxes
	for (var i=0;i<MAX_BOX;i++){
		var left=Math.round(Math.random()*24)*tankGame.box.width;
		var top=Math.round(Math.random()*14)*tankGame.box.width;
		while (
			(left<tankGame.box.width*2||left>(tankGame.playground.width-tankGame.box.width*2))
			&&(top<tankGame.box.width*2||top>(tankGame.playground.height-tankGame.box.width*2))
		){
			left=Math.round(Math.random()*24)*tankGame.box.width;
			top=Math.round(Math.random()*14)*tankGame.box.width;
		}		
		tankGame.boxSet.push(new box(left,top,tankGame.box.width,tankGame.box.height,true));
		$('#game').append("<div id='box"+i+"' class='box'></div>");
		$('#box'+i).css({'left':left,'top':top});
	}
});

function gameLoop(){
	moveMyTanks();
}

function gameLoopShort(){
	moveMyBullet();
}

function moveMyTanks(){
	var top=tankGame.myTank.top;
	var left=tankGame.myTank.left;
	if(tankGame.pressedKeys[KEY.A]){
		tankGame.myTank.direction=KEY.A;
		for (var i=0;i<tankGame.boxSet.length;i++){
			if (checkSingleCrash(tankGame.myTank,tankGame.boxSet[i])){
				$('#me').css('background','url(./img/tank_left.JPG)');
				return;
			}
		}
		if((left-tankGame.myTank.speed)>=0){
			$('#me').css('left',left-tankGame.myTank.speed);
			tankGame.myTank.left=left-tankGame.myTank.speed;
		}
		$('#me').css('background','url(./img/tank_left.JPG)');
	}
	if(tankGame.pressedKeys[KEY.D]){
		tankGame.myTank.direction=KEY.D;
		for (var i=0;i<tankGame.boxSet.length;i++){
			if (checkSingleCrash(tankGame.myTank,tankGame.boxSet[i])
			){
				$('#me').css('background','url(./img/tank_right.JPG)');
				return;
			}
		}
		if((left+tankGame.myTank.speed+tankGame.myTank.width)<=tankGame.playground.width){
			$('#me').css('left',left+tankGame.myTank.speed);
			tankGame.myTank.left=left+tankGame.myTank.speed;
		}
		$('#me').css('background','url(./img/tank_right.JPG)');
	}
	if(tankGame.pressedKeys[KEY.W]){
		tankGame.myTank.direction=KEY.W;
		for (var i=0;i<tankGame.boxSet.length;i++){
			if (checkSingleCrash(tankGame.myTank,tankGame.boxSet[i])){
				$('#me').css('background','url(./img/tank_up.JPG)');
				return;
			}
		}
		if((top-tankGame.myTank.speed)>=0){
			$('#me').css('top',top-tankGame.myTank.speed);
			tankGame.myTank.top=top-tankGame.myTank.speed;
		}
		$('#me').css('background','url(./img/tank_up.JPG)');
	}
	if(tankGame.pressedKeys[KEY.S]){
		tankGame.myTank.direction=KEY.S;
		for (var i=0;i<tankGame.boxSet.length;i++){
			if (checkSingleCrash(tankGame.myTank,tankGame.boxSet[i])){
				$('#me').css('background','url(./img/tank_down.JPG)');
				return;
			}
		}
		if((top+tankGame.myTank.speed+tankGame.myTank.width)<=tankGame.playground.height){
			$('#me').css('top',top+tankGame.myTank.speed);
			tankGame.myTank.top=top+tankGame.myTank.speed;
		}
		$('#me').css('background','url(./img/tank_down.JPG)');
	}
	if(tankGame.pressedKeys[KEY.SPACE]){
		if(tankGame.myBullet.alive==true){
			return;
		}else{
			tankGame.myBullet.alive=true;
			tankGame.myBullet.direction=tankGame.myTank.direction;
			if(tankGame.myBullet.direction==KEY.A){
				$('#myBullet').css({'left':left,
									'top':top+tankGame.myTank.width/2,
									'width':tankGame.myBullet.height,
									'height':tankGame.myBullet.width,
									})
				tankGame.myBullet.left=left;
				tankGame.myBullet.top=top+tankGame.myTank.width/2;
				tankGame.myBullet.startLeft=left;
				tankGame.myBullet.startTop=top+tankGame.myTank.width/2;
			}
			if(tankGame.myBullet.direction==KEY.D){
				$('#myBullet').css({'left':left+tankGame.myTank.width,
									'top':top+tankGame.myTank.width/2,
									'width':tankGame.myBullet.height,
									'height':tankGame.myBullet.width,
									})
				tankGame.myBullet.left=left+tankGame.myTank.width;
				tankGame.myBullet.top=top+tankGame.myTank.width/2;
				tankGame.myBullet.startLeft=left+tankGame.myTank.width;
				tankGame.myBullet.startTop=top+tankGame.myTank.width/2;
			}
			if(tankGame.myBullet.direction==KEY.W){
				$('#myBullet').css({'left':left+tankGame.myTank.width/2,
									'top':top,
									'width':tankGame.myBullet.width,
									'height':tankGame.myBullet.height,
									})
				tankGame.myBullet.left=left+tankGame.myTank.width/2;
				tankGame.myBullet.top=top;
				tankGame.myBullet.startLeft=left+tankGame.myTank.width/2;
				tankGame.myBullet.startTop=top;
			}
			if(tankGame.myBullet.direction==KEY.S){
				$('#myBullet').css({'left':left+tankGame.myTank.width/2,
									'top':top+tankGame.myTank.width,
									'width':tankGame.myBullet.width,
									'height':tankGame.myBullet.height,
									})
				tankGame.myBullet.left=left+tankGame.myTank.width/2;
				tankGame.myBullet.top=top+tankGame.myTank.width;
				tankGame.myBullet.startLeft=left+tankGame.myTank.width/2;
				tankGame.myBullet.startTop=top+tankGame.myTank.width;
			}
		}
	}
}

function moveMyBullet(){
	if(tankGame.myBullet.alive==true){
		var left=tankGame.myBullet.left;
		var top=tankGame.myBullet.top;
		var startLeft=tankGame.myBullet.startLeft;
		var startTop=tankGame.myBullet.startTop;
		if(tankGame.myBullet.direction==KEY.A){
			//out of range
			if(left<(startLeft-tankGame.myTank.shootRange)){
				$('#myBullet').css({'left':-9,'top':-9});
				tankGame.myBullet.alive=false;
				tankGame.myBullet.startLeft=-9;
				tankGame.myBullet.startTop=-9;
				return;
			}
			//crash box
			for (var i=0;i<tankGame.boxSet.length;i++){
				if (checkSingleCrash(tankGame.myBullet,tankGame.boxSet[i])){
					$('#myBullet').css({'left':-9,'top':-9});
					tankGame.myBullet.alive=false;
					return;
				}
			}
			$('#myBullet').css({'left':left-tankGame.myBullet.speed});
			tankGame.myBullet.left=left-tankGame.myBullet.speed;
		}
		if(tankGame.myBullet.direction==KEY.D){
			//out of range
			if(left>(startLeft+tankGame.myTank.shootRange)){
				$('#myBullet').css({'left':-9,'top':-9});
				tankGame.myBullet.alive=false;
				tankGame.myBullet.startLeft=-9;
				tankGame.myBullet.startTop=-9;
				return;
			}
			//crash box
			for (var i=0;i<tankGame.boxSet.length;i++){
				if (checkSingleCrash(tankGame.myBullet,tankGame.boxSet[i])){
					$('#myBullet').css({'left':-9,'top':-9});
					tankGame.myBullet.alive=false;
					return;
				}
			}
			$('#myBullet').css({'left':left+tankGame.myBullet.speed});
			tankGame.myBullet.left=left+tankGame.myBullet.speed;
		}
		if(tankGame.myBullet.direction==KEY.W){
			//out of range
			if(top<(startTop-tankGame.myTank.shootRange)){
				$('#myBullet').css({'left':-9,'top':-9});
				tankGame.myBullet.alive=false;
				tankGame.myBullet.startLeft=-9;
				tankGame.myBullet.startTop=-9;
				return;
			}
			//crash box
			for (var i=0;i<tankGame.boxSet.length;i++){
				if (checkSingleCrash(tankGame.myBullet,tankGame.boxSet[i])){
					$('#myBullet').css({'left':-9,'top':-9});
					tankGame.myBullet.alive=false;
					return;
				}
			}
			$('#myBullet').css({'top':top-tankGame.myBullet.speed});
			tankGame.myBullet.top=top-tankGame.myBullet.speed;
		}
		if(tankGame.myBullet.direction==KEY.S){
			//out of range
			if(top>(startTop+tankGame.myTank.shootRange)){
				$('#myBullet').css({'left':-9,'top':-9});
				tankGame.myBullet.alive=false;
				tankGame.myBullet.startLeft=-9;
				tankGame.myBullet.startTop=-9;
				return;
			}
			//crash box
			for (var i=0;i<tankGame.boxSet.length;i++){
				if (checkSingleCrash(tankGame.myBullet,tankGame.boxSet[i])){
					$('#myBullet').css({'left':-9,'top':-9});
					tankGame.myBullet.alive=false;
					return;
				}
			}
			$('#myBullet').css({'top':top+tankGame.myBullet.speed});
			tankGame.myBullet.top=top+tankGame.myBullet.speed;
		}
	}
}

function checkSingleCrash(run,sit){
	if(run.direction==KEY.A){
		if (
			(run.top>(sit.top-run.height))
			&&(run.top<(sit.top+sit.height))
			&&((run.left-sit.width)==sit.left)
			){
				return true;
			}
	}
	if(run.direction==KEY.D){
		if (
			(run.top>(sit.top-run.height))
			&&(run.top<(sit.top+sit.height))
			&&((run.left+run.width)==sit.left)
			){
				return true;
			}
	}
	if(run.direction==KEY.W){
		if (
			(run.left>(sit.left-run.width))
			&&(run.left<(sit.left+sit.width))
			&&((run.top-sit.height)==sit.top)
			){
				return true;
			}
	}
	if(run.direction==KEY.S){
		if (
			(run.left>(sit.left-run.width))
			&&(run.left<(sit.left+sit.width))
			&&((run.top+run.height)==sit.top)
			){
				return true;
			}
	}
	return false;
}
