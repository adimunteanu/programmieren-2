for file in ../ha02abgaben/*; do

    name=$(echo $file | cut -d "_" -f 1 | cut -d "/" -f 3)
    id=$(echo $file | cut -d "_" -f 2)
    
    echo "" >> output.csv
    echo -n $id',"'$name'"' >> output.csv

    echo ""
    echo "------------------------------------------------"
    echo $id $name

    echo "cleaning up..."
    rm -rf dest/
    rm -rf src/main/java/impl/

    echo "unzipping..."
    unzip -d dest/ "$file" > /dev/null

    echo "transferring files..."

    mv dest/src/main/java/impl/ src/main/java/impl/ >> /dev/null
    if  [ $? != 0 ]; then
        mv dest/*/src/main/java/impl/ src/main/java/impl/ >> /dev/null
        if  [ $? != 0 ]; then
            mv dest/*/*/src/main/java/impl/ src/main/java/impl/ >> /dev/null
            if  [ $? != 0 ]; then
                mv dest/*/*/*/src/main/java/impl/ src/main/java/impl/ >> /dev/null
                if  [ $? != 0 ]; then
                    mv dest/*/*/*/*/src/main/java/impl/ src/main/java/impl/ >> /dev/null
                    if  [ $? != 0 ]; then
                        mv dest/*/*/*/*/*/src/main/java/impl/ src/main/java/impl/ >> /dev/null
                        if  [ $? != 0 ]; then
                            echo "invalid folder"
                            echo -n ',0,"Ordnerstruktur ungültig. Manuelle Bewertung folgt."' >> output.csv
                            continue
                            fi
                    fi
                fi
            fi
        fi
    fi

    echo "folder ok"
    echo ""
    
    ./gradlew runeval
    if  [ $? != 0 ]; then
        echo "failed"
        echo -n ',0,"Build failed."' >> output.csv
    else
        echo "passed"
    fi
done