#!/usr/bin/env bash

#获取output
output="$(pwd)/output"
mkdir -p $output

echo $output

cd ..

echo "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
echo "[INFO]开始更新代码......"
echo "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"

git branch --set-upstream-to=origin/master master
git pull

echo "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
echo "[INFO]开始Maven打包......"
echo "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"



mvn  clean package  -Dmaven.test.skip=true -U

if [ $? -ne 0 ]; then
    echo ""
    echo "***********************************************************"
    echo "[INFO]Maven打包失败"
    echo "***********************************************************"
    exit 1
fi

cp template-demo/target/template-demo.jar ${output}
cp control.sh ${output}
chmod 744 ${output}/control.sh

if [ $? -ne 0 ]; then
    echo ""
    echo "***********************************************************"
    echo "[INFO]复制打包文件失败"
    echo "***********************************************************"
    exit 1
fi

echo ""
echo "***********************************************************"
echo "[INFO]Maven打包成功!!!!"
echo "***********************************************************"