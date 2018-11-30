env = "debug"
mbranch = "develop"
dingding = "23423423423424234234234234234234"
app_name = "cornerstone"

##仓库地址
mrepo='git@github.com:zloopk09/cornerstone.git'
##工程包名
mproject_dir='cornerstone'

startNotification(){
  title="#### "${JOB_NAME}"开始打包了 \n "
  info="> 应用："${app_name}"-"${env}"环境-"${BUILD_NUMBER}"号包 \n\n "
  url="> ###### [点击查看进度](${BUILD_URL}) \n\n "

  msg_text=\"${title}${info}${url}\"

  curl $dingding \
         -H 'Content-Type: application/json' \
         -d '
            {"msgtype": "markdown",
              "markdown": {
                  "title":"开始打包了",
                  "text":'"$msg_text"'
               }
            }'
}
cat << "EOF"
 "/*----------------------------*/"
 "/*-------  钉钉启动通知  -------*/"
 "/*----------------------------*/"
 "/*----------------------------*/"
EOF
startNotification

cat << "EOF"
 "/*----------------------------*/"
 "/*------- 主工程更新下载 -------*/"
 "/*----------------------------*/"
 "/*----------------------------*/"
EOF
if [ ! -d $mproject_dir ]; then
git clone -b ${mbranch} ${mrepo} -j6
fi
cd ${mproject_dir}
git stash
git fetch -p
git checkout ${mbranch}
git pull

cat << "EOF"
 "/*----------------------------*/"
 "/*-------  复制签名文件  -------*/"
 "/*----------------------------*/"
 "/*----------------------------*/"
EOF
cp ~/.sign/* .

cat << "EOF"
 "/*----------------------------*/"
 "/*-------   打包工程    -------*/"
 "/*----------------------------*/"
 "/*----------------------------*/"
EOF
chmod 777 gradlew
if [   ${env} = "debug" ]; then
  sh ./gradlew clean && sh ./gradlew assembleDebug --stacktrace
elif [ ${env} = "pre" ]; then
  sh ./gradlew clean && sh ./gradlew assemblePre --stacktrace
elif [ ${env} = "release" ]; then
  sh ./gradlew clean && sh ./gradlew assembleRelease --stacktrace
fi
filePath="./cornerstone/app/build/outputs/apk/cornerstone-${env}.apk"

cat << "EOF"
 "/*----------------------------*/"
 "/*-------  归档apk文件  -------*/"
 "/*----------------------------*/"
 "/*----------------------------*/"
EOF
cd ..
OUTPUT_DIR=output/${BUILD_NUMBER}
if [ ! -d $OUTPUT_DIR ]; then
mkdir -p $OUTPUT_DIR
fi
mv  ${filePath} "$OUTPUT_DIR/cornerstone-${env}-${BUILD_NUMBER}.apk"

cat << "EOF"

                 _-====-__-======-__-========-_____-============-__
               _(                                                 _)
            OO(                                                   )_
           0  (_                     打包完毕                        _)
         o0     (_                                                _)
        o         '=-___-===-_____-========-___________-===-dwb-='
      .o                                _________
     . ______          ______________  |         |      _____
   _()_||__|| ________ |            |  |_________|   __||___||__
  (BNSF 2018| |      | |            | __Y______00_| |_         _|
 /-OO----OO""="OO--OO"="OO--------OO"="OO-------OO"="OO-------OO"=P
#####################################################################

EOF