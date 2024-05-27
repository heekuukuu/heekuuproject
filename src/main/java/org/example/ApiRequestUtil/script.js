// 위치 정보를 가져오는 함수
function getLocation() {
    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var latitude = position.coords.latitude;
            var longitude = position.coords.longitude;
            var locationPara = document.getElementById("location");
            locationPara.textContent = "현재 위치: 위도 " + latitude + ", 경도 " + longitude;
        });
    } else {
        var locationPara = document.getElementById("location");
        locationPara.textContent = "Geolocation을 지원하지 않습니다.";
    }
}

// 페이지가 로드될 때 위치 정보 가져오기
window.onload = getLocation;
