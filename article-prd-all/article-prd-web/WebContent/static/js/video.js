var playVideo;
$(function(){
	var videoDom = null,
		$videoPlay = $('#videoPlay'),
		width = $videoPlay.width(),
		height = $videoPlay.height();
	//isFirstVideo = true;
	playVideo = function(){
		//if(isFirstVideo){
			$videoPlay.html('<div id="videoBox"></div>');
			videoDom = new TcPlayer('videoBox', {
				"m3u8": 'http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4',
				"autoplay" : true,
				"controls": "default",	//system,none,default
				"coverpic" : "",
				"width" :  width,
				"height" : height
//				listener: function (msg) {
//					if(msg.type == 'error'){
//                    }else if(msg.type == 'loadeddata'){ // IOS端第一次加载时此事件和PLAY事件都会执行
//                    	if(isFirstVideo){
//	                    	videoDom.play();
//	        	    		isFirstVideo = false;
//                    	};
//                    }else if(msg.type == 'play'){
//                    	if(!isFirstVideo){
//                    	};
//                    }else if(msg.type == 'pause'){
//                    	videoDom.pause();
//                    }else if(msg.type == 'ended'){
//                    };
//				}
			});
//		}else{
//			videoDom.play();
//		}
	}
});