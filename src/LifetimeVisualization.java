package src;

/*
1. 전체 프레임 만들기
2. 메인 페이지-데이터 입력 이동 버튼,설정 이동 버튼
3. 데이터 입력 화면:생일 입력 받기,칸의 기준을 월,년,10년 중에 선택,인터넷에서 날짜를 받아와 계산 + 메인 페이지로 돌아가기
4. 데이터 입력후 생성완료된 화면: 시각화 완료된 화면 보여줌, 이미지 추출 버튼으로 로컬 컴퓨터로 내보내기 + 메인 페이지로 돌아가기
5. 설정 화면: 언어, 이미지 추출시와 GUI화면에서 칸색과 배경색 설정 + 메인 페이지로 돌아가기
 */

public class LifetimeVisualization {
    public static void main(String[] args){
       new MainFrame();
    }
}
