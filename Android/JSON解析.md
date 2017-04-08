```java
private List<Person> parseJson(String json){
        try {
            JSONObject object=new JSONObject(json);
            int result=object.getInt("result");
            //将解析的json转化成实体的数据，创建一个ArrayList对象。
            List<Person> persons=new ArrayList<Person>();

            if(result==1){
                JSONArray personData=object.getJSONArray("personDate");
                //先创建数组，再遍历。
                for(int i=0;i<personData.length();i++){

                    Person personObject=new Person();
                    persons.add(personObject);

                    //取出数组中所对应的每一个object。
                    JSONObject person=personData.getJSONObject(i);
                    String name=person.getString("name");
                    int age=person.getInt("age");
                    String url=person.getString("url");
                    String school_info=person.getString("school_info");

                    personObject.setName(name);
                    personObject.setAge(age);
                    personObject.setUrl(url);
                    personObject.setSchool_info(school_info);

                }
                return persons;
            }else{
                Toast.makeText(context,"error1",Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
```