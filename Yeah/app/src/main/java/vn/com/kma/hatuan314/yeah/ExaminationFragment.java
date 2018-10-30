package vn.com.kma.hatuan314.yeah;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Random;

import vn.com.kma.hatuan314.Adapter.QuestionAdapter;
import vn.com.kma.hatuan314.model.MatrixQuestion;
import vn.com.kma.hatuan314.model.Question;
import vn.com.kma.hatuan314.other.RandomAlgorithm;
import vn.com.kma.hatuan314.other.SortAlgorithm;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExaminationFragment extends Fragment {
    private String TAG = "MATHVIEWINLIST";
    RecyclerView rcQuestionList;
    private QuestionAdapter math_list_adapter;
    public ArrayList<Question> questionList;

    ArrayList<MatrixQuestion> matrixQuestions;

    public ArrayList<Question> questions_same_thematic;
    public ArrayList<Question> questions_same_level;
    public ArrayList<Question> question_level_list_after_rand;
    public static ArrayList<Question> question_list_after_rand;

    Random rand;
    SortAlgorithm sortAlgorithm = new SortAlgorithm();

    public ExaminationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_examination, container, false);
        addControls(view);
        return view;
    }

    private void addControls(View view) {
        rcQuestionList =  view.findViewById(R.id.rcQuestionList);
        rcQuestionList.setHasFixedSize(false);
        rcQuestionList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        questionList = new ArrayList<>();
        questionList.add(new Question(1,4,1,
                "Tìm phần thực và phần ảo của số phức $z= 3+ 2i$",
                "Phần thực bằng 3, phần ảo bằng 2.",
                "Phần thực bằng 3, phần ảo bằng -2",
                " Phần thực bằng -3, phần ảo bằng 2.",
                " Phần thực bằng -3, phần ảo bằng -2."));
        questionList.add(new Question(2,4,1,
                "Tìm số thực thỏa mãn: $2+ ( 5-y)I = x-1 + 5i$",
                "x=  3 ;y=  0",
                "x= -6  ;y= 3",
                "x=6  ;y=-3 ",
                "x= 6 ;y= 3 "));
        questionList.add(new Question(3,4,1,
                "Số nghiệm của phương trình: $z + |z| =0 $",
                "Vô số",
                "1",
                "3",
                "4"));
        questionList.add(new Question(4,4,1,
                "Tìm tọa độ hai điểm biểu diễn hai số phức là nghiệm của phương trình $4z^2 + 2z + 25 =0$",
                "( -3\\2;2) và (-3\\2;-2)",
                "(3\\2;2) và (-3\\2;-2)",
                "(3\\2;-2) và (3\\2;2) ",
                "(3\\2;-2) và (3\\2;2) "));
        questionList.add((new Question(5,4,1,
                "Rut gọn: $z= i+ ( 2-4i) - (3-(2i)$",
                "z= -1 -i",
                "z= 1+ 2i",
                "z= 5+3i",
                "z= -1 -2i")));
        questionList.add(new Question(6,4,2,
                "Tìm số phức z thỏa mãn: $(2+ I ) z = 4z + 4 - 2i$",
                "z=-2",
                "z=2",
                "z= 22\\37 -16\\37i",
                "z= 26\\37 + 8\\37i"));
        questionList.add(new Question(7,4,3,
                "Trong mặt phẳng phức với hệ tọa độ Oxy, tập hợp các điểm biểu diễn số phức z thỏa mãn điều kiện |z-i|= 1 là",
                "Một đường tròn",
                "Một hình vuông",
                " Một đoạn thẳng.",
                "Một đường thẳng."));
        questionList.add(new Question(8,4,2,
                "Tập hợp điểm biểu diễn số phức z , biết: |z- ( 3-4i)|= 2  là",
                "Đường tròn tâm I(3; -4);R 2",
                "Đường tròn tâm I( -3; 4);R 2",
                "Đường tròn tâm I(3; -4);R 4.",
                "Đường tròn tâm I( -3; 4);R 4. n"));
        questionList.add(new Question(9,4,2,
                "Cho số phức z thỏa mãn. |z- 1| =3 . Tập hợp các điểm biểu diễn số phức z+ 1 -2i trên mặt phẳng phức là",
                "Đường tròn tâm (2; -2)  , bán kính bằng 3.",
                " Đường tròn tâm (1;0) , bán kính bằng 3.",
                "Đường tròn tâm (2;0) , bán kính bằng 3",
                "Đường tròn tâm (- 2; 2)  , bán kính bằng 3"));
        questionList.add(new Question(10,4,2,
                "\"Số phức z= 1 -2i, được biểu diễn trong mặt phẳng (Oxy) bởi điểm M có hoành độ bằng :",
                "1",
                "-1",
                "2",
                "-2"));
        questionList.add(new Question(11,4,3,
                "Trong mặt phẳng Oxy cho điểm A là điểm biểu diễn số phức z=1+2i, B là điểm thuộc đường thẳng y=2 sao cho tam giác OAB cân tại O. Điểm B là điểm biểu diễn của số phức",
                "1+2i",
                " 2‐i.",
                "1‐2i",
                "3+2i"));
        questionList.add(new Question(12,1,1,
                "Giá trị lớn nhất và giá trị nhỏ nhất của hàm số sau trên đoạn [-4;4] lần lượt là: $y= x^3 - 3x^2 - 9x + 35$",
                "40; -41",
                " 20; -2",
                "10; -11",
                "40; 31"));
        questionList.add(new Question(13,1,1,
                "Hàm số $y=  x^4 - 2x^2 + 1$ đồng biến trên các khoảng nào?",
                "1;0 và 1; + ∞",
                " -1;0",
                "1; + ∞",
                "∀x∈R\n"));
        questionList.add(new Question(14,1,1,
                "Tìm các giá trị của tham số m để hàm số $y= x^4 -2(m^2 +1)x^2 +1$ có 3 điểm cực trị thỏa mãn giá trị cực tiểu đạt giá trị lớn nhất$",
                "m =0",
                "m=1",
                "m=-1",
                "m=-3"));
        questionList.add(new Question(15,1,2,
                "Họ đường cong (Cm) $y = mx^3 – 3mx^2 + 2(m-1)x + 1$ đi qua những điểm cố định nào",
                "(0;1) ; B(1;-1) ; C(2;-3)",
                "A(0;1) ; B(1;-1) ; C(-2;3)",
                "A(-1;1) ; B(2;0) ; C(3;-2)",
                "Đáp án khác\n"));
        questionList.add(new Question(16,1,1,
                "Hàm số $y= ax^4 +bx^2 +c$ đạt cực đại tại A(0; - 3)  và đạt cực tiểu tại B( -1; -5) .Khi đó giá trị của a, b, c lần lượt là: ",
                "2; -4; -3",
                " -2; 4; -3",
                " -3; -1; -5",
                " 2; 4; -3\n"));
        questionList.add(new Question(17,1,2,
                " Tìm tất cả các giá trị của tham số k để phương trình $4x^2(1-x^2) = 1- k$ có bốn nghiệm thực phân biệt",
                "0<k<1",
                "0<k<2",
                "-1<k<2",
                "K<3\n"));
        questionList.add(new Question(18,1,3,
                "Viết phương trình tiếp tuyến d của đồ thị hàm số $f(x) = x^3 + 2x^2 + x - 4$ tại giao điểm của đồ thị hàm số với trục hoành.",
                "y= 8x-8",
                "y= 2x - 1 "
                ,"y= 1 ",
                "y= x- 7 "));
        questionList.add(new Question(19,1,3,
                "Cho hàm số $f(x) = x^3-3x^2$ , tiếp tuyến của đồ thị có hệ số góc k= -3 là",
                "y + 2 = -3( x- 1) ",
                "Y-2 -x(x-1) =0 ",
                "y = -3( x-1) +2",
                "y-2 = -3( x-1)"));
        questionList.add(new Question(20,1,2,
                "Hàm số $y= x^3 - 3mx + 5$ nghịch biến trong khoảng 1;1 thì m bằng",
                "1",
                "2",
                "3",
                "-1"));

        questionList = sortAlgorithm.SortThematic(questionList);
        question_list_after_rand = new ArrayList<>();

        rand = new Random();
        question_level_list_after_rand = rand_question(questionList);
        for (int i=0; i<question_level_list_after_rand.size(); i++){
            int rand_numb = rand.nextInt(question_level_list_after_rand.size());
            Question question = question_level_list_after_rand.get(rand_numb);
            question_list_after_rand.add(question);
        }


        math_list_adapter = new QuestionAdapter(getContext(),question_list_after_rand);
        rcQuestionList.setAdapter(math_list_adapter);

        Log.d(TAG,"Layout Views Initialized");
    }

    public ArrayList<Question> rand_question( ArrayList<Question> questionList){
        /* Get list question same thematic */
        questions_same_thematic= new ArrayList<>();
        /* Get list question same level */
        questions_same_level = new ArrayList<>();

        question_level_list_after_rand = new ArrayList<>();

        /* id_thematic = 1 */
        questions_same_thematic = sort_thematic_list(questionList, questions_same_thematic, 1);
        questions_same_thematic = sortAlgorithm.SortLevel(questions_same_thematic);

        rand = new Random();
        for (int level=1; level<=3; level++){
            questions_same_level = sort_level_list(questions_same_thematic, questions_same_level, level);
            for (int j=1; j<3; j++){
                Question question;
                int question_numb = rand.nextInt(questions_same_level.size());
                question = questions_same_level.get(question_numb);
                question_level_list_after_rand.add(question);
                questions_same_level.remove(question_numb);
            }
            questions_same_level.clear();
        }

        /* id_thematic = 4 */
        questions_same_thematic = sort_thematic_list(questionList, questions_same_thematic, 4);
        questions_same_thematic = sortAlgorithm.SortLevel(questions_same_thematic);

        rand = new Random();
        for (int level=1; level<=3; level++){
            questions_same_level = sort_level_list(questions_same_thematic, questions_same_level, level);
            for (int j=1; j<3; j++){
                Question question;
                int question_numb = rand.nextInt(questions_same_level.size());
                question = questions_same_level.get(question_numb);
                question_level_list_after_rand.add(question);
                questions_same_level.remove(question_numb);
            }
            questions_same_level.clear();
        }

        return question_level_list_after_rand;
    }

    public ArrayList<Question> sort_thematic_list(ArrayList<Question> question_big_list, ArrayList<Question> question_small_list, int id_thematic){
        Question question = question_big_list.get(0);
        do {
            question_small_list.add(question);
            question_big_list.remove(question);

            if (question_big_list.size()!=0){
                question = question_big_list.get(0);
            } else {
                break;
            }
        }while(question.getId_thematic()==id_thematic);
        return question_small_list;
    }

    public ArrayList<Question> sort_level_list(ArrayList<Question> question_big_list, ArrayList<Question> question_small_list, int level){
        Question question_thematic = question_big_list.get(0);
        do {
            question_small_list.add(question_thematic);
            question_big_list.remove(question_thematic);
            if (question_big_list.size()!=0){
                question_thematic = question_big_list.get(0);
            } else {
                break;
            }
        }while(question_thematic.getLevel()==level);
        return question_small_list;
    }

}
