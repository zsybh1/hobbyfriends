package com.zsybh1.hobbyfriends.Topic

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.zsybh1.hobbyfriends.Model.Topic

class TopicDetailViewModel : ViewModel() {
    var topicId = 0L

    val dataList = mutableListOf<Any>()

    companion object{
        private const val TAG = "TopicViewModel"
    }

    fun getTopic(){
        dataList.clear()
        dataList.add(Gson().fromJson(getTopicFromNet(), Topic::class.java))
        Log.d(TAG, dataList[0].toString())
        for (comment in (dataList[0] as Topic).comments) {
            dataList.add(comment)
        }
    }

    fun getTopicFromNet() : String{
        return """
            {
        "id": 1000000000,
        "ownerId": 1,
        "username":"匿名用户",
        "headImg":"https://pic2.zhimg.com/aadd7b895_xs.jpg?source=1940ef5c",
        "imgUrl": [
            "https://pic4.zhimg.com/80/v2-970c43645f17c5a7adb79b5aa554d303_720w.jpg?source=1940ef5c",
            "https://pic4.zhimg.com/80/v2-cba9f325a79d9321e4ac8912910e3b69_720w.jpg?source=1940ef5c"
        ],
        "comments": [
            {
                "commentId": 1234,
                "replyToId": 0,
                "replyToName": null,
                "userId": 2,
                "username": "Mithrandir zhao",
                "content": "真的，我和你一样的感觉，挺你！",
                "headImg": "https://pic1.zhimg.com/v2-9cb1b4e99f24c8d83db8769616d55c72_im.jpg",
                "likes": 7,
                "isLiked": false,
                "sendDate": "2021-01-13 19:19:37",
                "subComments": []
            },
            {
                "commentId": 1235,
                "replyToId": 0,
                "replyToName": null,
                "userId": 3,
                "username": "jack",
                "content": "为什么非得保,自己考不行吗？",
                "headImg": "https://pic4.zhimg.com/da8e974dc_im.jpg",
                "likes": 19,
                "isLiked": false,
                "sendDate": "2021-01-13 19:28:37",
                "subComments": [
                    {                
                        "commentId": 1236,
                        "replyToId": 3,
                        "replyToName": "jack",
                        "userId": 4,
                        "username": "Richard",
                        "content": "说明你没有参加过考研",
                        "headImg": "https://pic4.zhimg.com/v2-064e11d554e917b1d6501454a969417b_im.jpg",
                        "likes": 50,
                        "isLiked": false,
                        "sendDate": "2021-01-13 19:29:37",
                        "subComments": []
                    },
                    {                
                        "commentId": 1237,
                        "replyToId": 4,
                        "replyToName": "Richard",
                        "userId": 3,
                        "username": "jack",
                        "content": "这么武断，我都研究生毕业了[捂脸]",
                        "headImg": "https://pic4.zhimg.com/da8e974dc_im.jpg",
                        "likes": 39,
                        "isLiked": false,
                        "sendDate": "2021-01-13 19:30:37",
                        "subComments": []
                    },
                    {                
                        "commentId": 1238,
                        "replyToId": 3,
                        "replyToName": "jack",
                        "userId": 5,
                        "username": "我要把你做得好次",
                        "content": "那你岂不是更不了解如今考研的难度了？",
                        "headImg": "https://pic3.zhimg.com/v2-8fa9c3832b410ea0b28ccdd6f3f45c64_im.jpg",
                        "likes": 33,
                        "isLiked": false,
                        "sendDate": "2021-01-13 19:31:37",
                        "subComments": []
                    },
                    {                
                        "commentId": 1237,
                        "replyToId": 5,
                        "replyToName": "我要把你做得好次",
                        "userId": 3,
                        "username": "jack",
                        "content": "你觉得保研非文化课占比很大，那考研可都是文化课，你真有实力还考不上[好奇]",
                        "headImg": "https://pic4.zhimg.com/da8e974dc_im.jpg",
                        "likes": 14,
                        "isLiked": false,
                        "sendDate": "2021-01-13 19:30:37",
                        "subComments": []
                    }
                ]
            },
            {                
                "commentId": 1238,
                "replyToId": 0,
                "replyToName": null,
                "userId": 6,
                "username": "上网不涉密",
                "content": "现在小学期还没改嘛 web那门课真的 授课质量太差了 程序设计倒挺锻炼人的[调皮]",
                "headImg": "https://pic3.zhimg.com/v2-18313b0fe29c5abfabc0f2f85f28cc71_im.jpg",
                "likes": 9,
                "isLiked": false,
                "sendDate": "2021-01-16 08:11:22",
                "subComments": [
                    {
                        "commentId": 1239,
                        "replyToId": 6,
                        "replyToName": "上网不涉密",
                        "userId": 7,
                        "username": "Endericedragon",
                        "content": "程序设计差点没被老师气死",
                        "headImg": "https://pic3.zhimg.com/v2-18313b0fe29c5abfabc0f2f85f28cc71_im.jpg",
                        "likes": 0,
                        "isLiked": false,
                        "sendDate": "2021-01-19 14:23:22",
                        "subComments": []
                    }
                ]
            },
            {                
                "commentId": 1240,
                "replyToId": 0,
                "replyToName": null,
                "userId": 9,
                "username": "连城",
                "content": "跳个啦啦操加的综测比发sci高，但我必不可能为了奖学金和保研去跳啦啦操。上学期我们一起自习的两个好兄弟纯成绩专业第一第二 都是二等奖学金.这不就是欺负老实人吗",
                "headImg": "https://pic1.zhimg.com/v2-28dba1409d3cba94335bf511c713ae91_im.jpg",
                "likes": 74,
                "isLiked": false,
                "sendDate": "2021-01-16 09:45:22",
                "subComments": [
                    {
                        "commentId": 1231,
                        "replyToId": 9,
                        "replyToName": "连城",
                        "userId": 1,
                        "username": "匿名用户",
                        "content": "我大一那会差不多就是你这种情况了",
                        "headImg": "https://pic3.zhimg.com/aadd7b895_s.jpg?source=06d4cd63",
                        "likes": 0,
                        "isLiked": false,
                        "sendDate": "2021-01-19 14:25:22",
                        "subComments": []
                    }
                ]
            },
            {                
                "commentId": 1243,
                "replyToId": 0,
                "replyToName": null,
                "userId": 10,
                "username": "尘封的记忆i",
                "content": "综测真的是很恶心。我在某211医科就读临床，真是卷啊，成绩基本都差不多，主要就在其他加分上，乱七八糟学生会之类的直接加四五分，写个字画个画都能加好几分，还有花里胡哨的互联网＋。我就很奇怪，将来当医生这些有什么用？这对成为一个好医生纯属无用，但规则就是这样，没能力就注定不能保研，只能自己考喽！[流泪]",
                "headImg": "https://pic4.zhimg.com/v2-171a05f4f159ea1ddaa17bc9a388dc95_im.jpg",
                "likes": 0,
                "isLiked": false,
                "sendDate": "2021-02-04 09:01:22",
                "subComments": []
            }
        ],
        "title": "网传北理工理教发生自杀事件，是真的吗？发生什么了？",
        "content": "我的话可能会有点偏激，为了自身安全还是先匿了。\n我现在是北理大二一名普普通通的计科学生。大一刚刚入学的时候我心气高啊，觉得天不怕地不怕。保研？轻轻松松！那会觉得自己的未来是光明的，我要走的路是清晰的，再加上大一的课程确实简单，我很容易就拿到了一个很高的排名。我那时也知道，最后保研是需要看综测成绩的，而且综测占的比重实际上相当多。为了综测需要做很多事情——当干部，听第二课堂，凑志愿时长，等等。本着“安能摧眉折腰事权贵，使我不得开心颜”的原则，我没有刻意去做以上任何一件事情。结果就是，最后统计综测成绩的时候，我能填的东西屈指可数。而我的综合排名在考虑综测之后，也直接飞了出去。\n那时候我就在思考一件事——保研的选拔，究竟是在选拔什么？真正适合做研究的，难道不应当是勤恳学习的人吗？当干部，做志愿，为他人做贡献固然是值得鼓励的，但这与一个人的学习和研究能力是否有直接的关系？把这些部分的加分调的这么高，是否真的合理？\n大二的到来让我愈发认清现实。首先是专业分流，最顶尖的一批人几乎全部拥挤到计科来了，这是计科最卷的一年，从来没有过这种情况，但那时我仍然天真的觉得，我只要努力，仍然能够脱颖而出。之后小学期开始，总共就两门课，程序设计和web。程序设计我上的很开心，我觉得这是门有意义的课，我的能力确实得到了锻炼。但是web却上的迷迷糊糊的，短短的学时内想把一堆的知识塞给我们，我觉得很可笑。而且从同学那边听说，就连任课老师本人也认为这门课设置的有问题。但是谁有办法呢，课还得上，作业还得交。\n最后的大作业，老师的要求本来是很简单的，就是一些基础的不能再基础的东西，我相信哪怕没有好好上课，自己查博客也能完成。但是内卷就这么开始了，各路大佬做的页面一个比一个神仙。第一次高自由度的大作业就给我留下了极为深刻的印象。\n我自然是不愿意一起去卷的。我觉得web这个东西，我应该很长一段时间内用不到它。而且就卷这么一次大作业，我实际上并学不到什么东西。我相信现在应该没有什么同学还记得和那次课有关的太多内容了。但是我要是去卷的话，我必然要耗费大把的时间，我为什么不用这些时间去看看算法呢？\n当然，最后为了让成绩不至于太难看，我还是稍微多做了一些东西。但是打那时起，我就意识到内卷对我而言没有任何意义。大学之前，成绩是评判学生的唯一标准，因为最后的高考就是以此为标准的；但是我上大学是为了我出社会做准备的，我应当着眼于提升自己的专业技能，我应当趁着这几年时光做有意义的事情。而不是内卷，而不是为了可怜的那几个综测分去做一些我既不愿意做，也不会对我有提升的事情。\n我身边不少朋友是高中搞信息竞赛的，而我高中就是个搞物理竞赛的。先天而言我在这个专业，就缺少优势。而且15%的保研名额，更是意味着你的成绩不仅仅需要非常好 而且更不允许任何意外情况。你今天一个不舒服，考试低个几分，最后很有可能就没法保研了；运气不好，遇见个sb老师，最后的成绩就是低那么一些，最后很可能就没法保研了。\n条条大路通罗马，而有的人就生在罗马。我不是生在罗马的那批人，我可能也没别人跑的快。但这不意味着我就放弃希望，我就非要固执的一条道走到黑。社会的评价体系是多元的，我们不应当把自己绑死在学校强加给你的评价体系中。我们要认识到这个评价体系可能是不合理的，而且很可能并不适合自己。跳不出这一隅是很可悲的。我很庆幸我自己能较早的看清楚问题，然后跳出来。\n当然，跳不出去的原因很可能是多方面的。我高中的好兄弟最近就在疯狂的内卷。我问他为什么，他说他家里最近经济情况不是很好，他必须拿到奖学金，他不想给家里增加负担。我家里的经济情况虽然不能说多好，但是还没有到非要拿奖学金才能过下去的地步。他们学校的评价体系很有问题，我很想劝他跳出内卷，但我甚至不知道怎么开口。有些人可能都有这样或者那样的原因，逼着他们不得不去内卷，但是我想说的是，一定要把这一切考虑清楚。无底线的牺牲自我，是否真的值得？这份责任我们是否真的有能力去承担？我觉得随意去背负不应当属于自己的东西，只会导致悲剧。\n我觉得人最本质的诉求就是寻求幸福。我其实一直无法理解自杀的人。我可以生硬的去解释说，自杀无非是感觉到人生没有希望了。但是我从来不会觉得人生没有希望，可能是我遇到的挫折不够多吧。但是我觉得，只要活着，就有可能性。现状可能很糟糕，明天可能更糟糕，但是只要不放弃 总有一天会变得更好。自杀是在逃避问题，但逃避解决不了任何问题。现在可能很痛苦，但是我还有要做的事情，我还有没有完成的梦想，我不能因为一时的不愉快就无视这些，去放弃这些。我人固有一死，我不想我没有意义的死去。就算死，我也希望我的死能让这个社会更好一些。共产主义还没实现，路灯上的装饰物还不够呢！就这么倒下，还太早了。\n只能说，希望人没事吧。卷不过真的不要去卷了，没有什么是比自己生命更重要的东西。无论遇到什么事情，也不要随随便便地想到自杀，生命还在，就有希望。",
        "views": 50,
        "likes": 840,
        "sendDate": "2021-01-13 18:35:37",
        "isLiked": false
    }
        """.trimIndent()
    }
}