package othersPackage;

import org.eclipse.jdt.core.dom.*;
//import org.eclipse.jdt.core.dom.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Operation {

    public GraphNode root;
    Stack<GraphNode> graphNodeStack = new Stack<GraphNode> ();
    Set<GraphNode> assertNodeSet = new HashSet<>();
    Set<GraphNode> tryBodySet = new HashSet<>();
    Map<IVariableBinding,Set<GraphNode>> mapForVariableBinding = new HashMap<>();
    Map<IMethodBinding,Set<GraphNode>> mapForMethodInvocationBinding = new HashMap<>();
    Map<IMethodBinding,GraphNode> mapForMethodDeclarationBinding = new HashMap<>();
    int i = 0;
    int marker = 0;
    int marking = 0;
    IVariableBinding s;
    public Set<IVariableBinding> setOfVariableBinding = new HashSet<>();
    public Set<IMethodBinding> setOfMethodBinding = new HashSet<>();
    ASTNode startingNode;
    Set<ASTNode> nodesForBackwardSlicing = new TreeSet<>(Comparator.comparing(ASTNode::getStartPosition));
    Set<ASTNode> nodesForForwardSlicing = new TreeSet<>(Comparator.comparing(ASTNode::getStartPosition));

    public String readFileToString(String filePath) throws IOException {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        char[] buf = new char[10];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }

        reader.close();

        return  fileData.toString();
    }

    public void parse(String str) {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setResolveBindings(true);
        parser.setSource(str.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        //parser.setEnvironment(new String[] {"C:\\Users\\ASUS\\Desktop\\demo\\out\\production\\demo"}, new String[] {"C:\\Users\\ASUS\\Desktop\\demo\\src"}, null, true);
        parser.setEnvironment(null, null, null, true);
        parser.setUnitName("Saal.java");
        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);


        root = new GraphNode();
        graphNodeStack.push(root);

        cu.accept(new ASTVisitor() {
            public void preVisit (ASTNode node) {

            }

            public boolean visit (IfStatement node) {
                //System.out.println(node.getExpression());
                //System.out.println(node);
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                graphNodeStack.push(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                if(marker == 1)
                    tryBodySet.add(temp);
                return true;
            }

            public boolean visit (SynchronizedStatement node) {
                //System.out.println(node.getExpression());
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                graphNodeStack.push(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                if(marker == 1)
                    tryBodySet.add(temp);
                return true;
            }

            public boolean visit (ForStatement node) {
                //System.out.println(node.getExpression());
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                graphNodeStack.push(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                if(marker == 1)
                    tryBodySet.add(temp);
                return true;
            }

            public boolean visit (EnhancedForStatement node) {
                //System.out.println(node.getExpression());
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                graphNodeStack.push(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                if(marker == 1)
                    tryBodySet.add(temp);
                return true;
            }

            public boolean visit (WhileStatement node) {
                //System.out.println(node);
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                graphNodeStack.push(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                if(marker == 1)
                    tryBodySet.add(temp);
                return true;
            }

            public boolean visit (DoStatement node) {
                //System.out.println(node);
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                graphNodeStack.push(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                if(marker == 1)
                    tryBodySet.add(temp);
                return true;
            }

            public boolean visit (SwitchStatement node) {
                //System.out.println(node.getExpression());
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                graphNodeStack.push(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                if(marker == 1)
                    tryBodySet.add(temp);
                return true;
            }

            public boolean visit (SwitchCase node) {
                //System.out.println(node.getExpression());
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                graphNodeStack.push(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                if(marker == 1)
                    tryBodySet.add(temp);
                return true;
            }

            public boolean visit (LabeledStatement node) {
                //System.out.println(node.getExpression());
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                graphNodeStack.push(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                return true;
            }

            public boolean visit (TryStatement node) {

                /*List<StructuralPropertyDescriptor> list = node.structuralPropertiesForType();
                for(StructuralPropertyDescriptor a : list)
                {
                    System.out.println(a.getId());
                }*/
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                graphNodeStack.push(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                marker = 1;
                return true;
            }

            public boolean visit (CatchClause node) {
                //System.out.println(node.getExpression());
                marker = 0;
                GraphNode temp;
                temp = new GraphNode(node);
                graphNodeStack.push(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                for(GraphNode g: tryBodySet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                return true;
            }

            public boolean visit (ExpressionStatement node) {
                //System.out.println(node);
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                graphNodeStack.add(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                if(marker == 1)
                    tryBodySet.add(temp);
                return true;
            }

            public boolean visit (SimpleName node) {
                IBinding bind = node.resolveBinding();
                if(mapForVariableBinding.containsKey(bind))
                {
                    //System.out.println(node);
                }
                return false;
            }

            public boolean visit (BreakStatement node) {
                //System.out.println(node);
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                if(marker == 1)
                    tryBodySet.add(temp);
                return false;
            }

            public boolean visit (ContinueStatement node) {
                //System.out.println(node);
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                if(marker == 1)
                    tryBodySet.add(temp);
                return false;
            }

            public boolean visit (ReturnStatement node) {
                //System.out.println(node);
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                if(marker == 1)
                    tryBodySet.add(temp);
                return false;
            }

            public boolean visit (ThrowStatement node) {
                //System.out.println(node);
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                if(marker == 1)
                    tryBodySet.add(temp);
                return false;
            }

            public boolean visit (AssertStatement node) {
                //System.out.println(node);
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                assertNodeSet.add(temp);
                if(marker == 1)
                    tryBodySet.add(temp);
                return false;
            }

            public boolean visit (EmptyStatement node) {
                //System.out.println(node);
                GraphNode temp;
                temp = new GraphNode(node);
                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                return false;
            }

            public void endVisit (ForStatement node) {
                graphNodeStack.pop();
            }

            public void endVisit (EnhancedForStatement node) {
                graphNodeStack.pop();
            }

            public void endVisit (WhileStatement node) {
                graphNodeStack.pop();
            }

            public void endVisit (DoStatement node) {
                GraphNode temp =  graphNodeStack.pop();
                graphNodeStack.peek().children.addAll(temp.children);
                for(GraphNode g : temp.children) {
                    g.parents.add(graphNodeStack.peek());
                }
            }

            public void endVisit (SwitchStatement node) {
                graphNodeStack.pop();
            }

            public void endVisit (SwitchCase node) {
                graphNodeStack.pop();
            }

            public void endVisit (ExpressionStatement node) {
                graphNodeStack.pop();
            }

            public void endVisit (LabeledStatement node) {
                graphNodeStack.pop();
            }

            public void endVisit (IfStatement node) {
                graphNodeStack.pop();
            }

            public void endVisit (SynchronizedStatement node) {
                graphNodeStack.pop();
            }

            public void endVisit (TryStatement node) {
                graphNodeStack.pop();
                tryBodySet.clear();
            }

            public void endVisit (CatchClause node) {
                graphNodeStack.pop();
            }

            public void endVisit (VariableDeclarationStatement node) {
                graphNodeStack.pop();
            }

            public boolean visit (SingleVariableDeclaration node) {
                //System.out.println(node);
                IVariableBinding bind = node.resolveBinding();
                Set<GraphNode> set = new HashSet<>();
                set.add(graphNodeStack.peek());
                mapForVariableBinding.put(bind,set);
                return true;
            }

            public boolean visit (VariableDeclarationFragment node) {
                //System.out.println(node);
                IVariableBinding bind = node.resolveBinding();
                Set<GraphNode> set = new HashSet<>();
                set.add(graphNodeStack.peek());
                mapForVariableBinding.put(bind,set);
               /* for (Map.Entry<IVariableBinding,Set<GraphNode>> entry : map.entrySet())
                {
                    System.out.println("Key = " + entry.getKey() +
                            ", Value = " + entry.getValue());
                    Set<GraphNode> nodeSet = entry.getValue();

                    for (GraphNode gn: nodeSet)
                    {
                        System.out.println(gn.node);
                    }

                }*/
                node.accept(new ASTVisitor() {
                    public boolean visit (SimpleName child)
                    {
                        //System.out.println(child);
                        if(marking == 0)
                        {
                            s = (IVariableBinding) child.resolveBinding();
                            marking = 1;
                        }
                        else
                        {
                            //System.out.println(child);
                            setOfVariableBinding.add((IVariableBinding) child.resolveBinding());
                        }
                        return true;
                    }
                });
                return true;
            }

            public void endVisit (VariableDeclarationFragment node) {
                for (IVariableBinding v : setOfVariableBinding)
                {
                    graphNodeStack.peek().getParents().addAll(mapForVariableBinding.get(v));
                    for(GraphNode g : mapForVariableBinding.get(v))
                    {
                        g.children.add(graphNodeStack.peek());
                    }
                    mapForVariableBinding.get(s).add(graphNodeStack.peek());
                }
                setOfVariableBinding.clear();
                marking = 0;
            }

            public boolean visit (VariableDeclarationStatement node) {
                //System.out.println(node);

                GraphNode temp;
                temp = new GraphNode(node);
                startingNode = temp.node;

                temp.parents.add(graphNodeStack.peek());
                graphNodeStack.peek().children.add(temp);
                graphNodeStack.add(temp);
                for(GraphNode g: assertNodeSet)
                {
                    g.children.add(temp);
                    temp.parents.add(g);
                }
                if(marker == 1)
                    tryBodySet.add(temp);

                return true;
            }

            /*public boolean visit (ArrayAccess node)
            {
                System.out.println(node);
                node.accept(new ASTVisitor() {

                    public boolean visit (SimpleName child)
                    {
                        //System.out.println(child);
                        if(child.resolveTypeBinding() instanceof PrimitiveType)
                        {
                            if(marking == 0)
                            {
                                s = (IVariableBinding) child.resolveBinding();
                                marking = 1;
                            }
                            else
                            {
                                setOfVariableBinding.add((IVariableBinding) child.resolveBinding());
                            }
                        }
                        return false;
                    }

                    public boolean visit (QualifiedName child)
                    {
                        if(!(child.resolveTypeBinding() instanceof PrimitiveType))
                        {
                            //System.out.println("asd");

                        }
                        return false;
                    }
                });

                return false;
                *//*System.out.println(child.getArray().structuralPropertiesForType().get(0));
                child.accept(new ASTVisitor() {

                    public boolean visit (SimpleName arrayName){
                        System.out.println(arrayName);
                        if(marking == 0)
                        {
                            s = (IVariableBinding) arrayName.resolveBinding();
                            marking = 1;
                        }
                        else
                        {
                            setOfVariableBinding.add((IVariableBinding) arrayName.resolveBinding());
                        }
                        return false;
                    }
                });

                        *//**//*System.out.println(child.getArray());
                        if(child.getArray().resolveTypeBinding() instanceof PrimitiveType)
                        {
                            if(marking == 0)
                            {
                                s = (IVariableBinding) child.getArray().resolveBinding();
                                marking = 1;
                            }
                            else
                            {
                                setOfVariableBinding.add((IVariableBinding) child.resolveBinding());
                            }
                        }*//**//*
                return false;*//*
            }*/

            public boolean visit (Assignment node) {
                //System.out.println(node);
                node.accept(new ASTVisitor() {

                    public boolean visit (SimpleName child)
                    {
                        //System.out.println(child);
                        if(marking == 0)
                        {
                            //System.out.println("dhuksi");
                            s = (IVariableBinding) child.resolveBinding();
                            marking = 1;
                        }
                        else
                        {
                            setOfVariableBinding.add((IVariableBinding) child.resolveBinding());
                        }
                        return false;
                    }

                    public boolean visit (QualifiedName child)
                    {
                        if(!(child.resolveTypeBinding() instanceof PrimitiveType))
                        {
                            //System.out.println("asd");

                        }
                        return false;
                    }
                });

                return false;
            }

            public void endVisit (Assignment node) {
                if(setOfVariableBinding.isEmpty())
                {
                    GraphNode tempuNode = null;
                    for(GraphNode g : mapForVariableBinding.get(s))
                    {
                        if(g.node instanceof VariableDeclarationStatement)
                        {
                            tempuNode = g;
                            break;
                        }
                    }
                    Set<GraphNode> tempuSet = new HashSet<>();
                    tempuSet.add(tempuNode);
                    tempuSet.add(graphNodeStack.peek());
                    mapForVariableBinding.replace(s,mapForVariableBinding.get(s),tempuSet);

                    tempuNode.children.add(graphNodeStack.peek());
                    graphNodeStack.peek().parents.add(tempuNode);
                }
                else
                {
                    for (IVariableBinding v : setOfVariableBinding)
                    {
                        graphNodeStack.peek().getParents().addAll(mapForVariableBinding.get(v));
                        for(GraphNode g : mapForVariableBinding.get(v))
                        {
                            g.children.add(graphNodeStack.peek());
                        }
                        mapForVariableBinding.get(s).add(graphNodeStack.peek());
                    }
                }
                setOfVariableBinding.clear();
                marking = 0;
            }

            public boolean visit (MethodInvocation node) {

                //System.out.println(node);
                List<Expression> list = node.arguments();
                //System.out.println(list);
                for(Expression e : list)
                {
                    e.accept(new ASTVisitor() {
                        public boolean visit (SimpleName child) {
                            //System.out.println(child);
                            //System.out.println(setOfVariableBinding.size());
                            setOfVariableBinding.add((IVariableBinding) child.resolveBinding());
                            //System.out.println(setOfVariableBinding.size());
                            for (IVariableBinding v : setOfVariableBinding)
                            {
                                for(GraphNode g : mapForVariableBinding.get(v))
                                {
                                    //System.out.println(g.node);
                                }
                            }
                            return false;
                        }
                    });
                }
                return false;
            }

            public void endVisit (MethodInvocation node) {

                IMethodBinding bind = node.resolveMethodBinding();

                if (mapForMethodInvocationBinding.containsKey(bind))
                {
                    mapForMethodInvocationBinding.get(bind).add(graphNodeStack.peek());
                }
                else
                {
                    Set<GraphNode> set = new HashSet<>();
                    set.add(graphNodeStack.peek());
                    mapForMethodInvocationBinding.put(bind, set);
                }

                if (mapForMethodDeclarationBinding.containsKey(bind))
                {
                    for(GraphNode g : mapForMethodInvocationBinding.get(bind))
                    {
                        mapForMethodDeclarationBinding.get(bind).parents.add(g);
                        g.children.add(mapForMethodDeclarationBinding.get(bind));
                    }
                }

                //System.out.println(node);
                for (IVariableBinding v : setOfVariableBinding)
                {
                    graphNodeStack.peek().getParents().addAll(mapForVariableBinding.get(v));
                    for(GraphNode g : mapForVariableBinding.get(v))
                    {
                        //System.out.println(g.node);
                        g.children.add(graphNodeStack.peek());
                    }
                }
                setOfVariableBinding.clear();
            }

            public boolean visit (MethodDeclaration node) {
                //setOfMethodBinding.add((IMethodBinding) node.resolveBinding());
                System.out.println(node);

                GraphNode temp;
                temp = new GraphNode(node);

                IMethodBinding bind = node.resolveBinding();

                mapForMethodDeclarationBinding.put(bind, temp);

                if (mapForMethodInvocationBinding.containsKey(bind))
                {
                    for(GraphNode g : mapForMethodInvocationBinding.get(bind))
                    {
                        temp.parents.add(g);
                        g.children.add(temp);
                    }
                }

                //graphNodeStack.push(temp);
                return true;
            }

            public void endVisit (MethodDeclaration node) {
                //graphNodeStack.pop();
            }

            public boolean visit (PrefixExpression node) {
                node.accept(new ASTVisitor() {
                    public boolean visit (SimpleName child) {
                        s = (IVariableBinding) child.resolveBinding();
                        return false;
                    }
                });
                return false;
            }

            public void endVisit (PrefixExpression node) {
                mapForVariableBinding.get(s).add(graphNodeStack.peek());
            }

            public boolean visit (PostfixExpression node) {
                node.accept(new ASTVisitor() {
                    public boolean visit (SimpleName child) {
                        s = (IVariableBinding) child.resolveBinding();
                        return false;
                    }
                });
                return false;
            }

            public void endVisit (PostfixExpression node) {
                mapForVariableBinding.get(s).add(graphNodeStack.peek());
            }


            public void postVisit (ASTNode node) {

            }
        });

    }

    void recursionForForwardSlicing (GraphNode g)
    {
        /*if(g==null)
            return;*/

        nodesForForwardSlicing.add(g.node);

        for(GraphNode gg : g.children)
        {
            recursionForForwardSlicing(gg);
        }
    }

    GraphNode getStartingNode (GraphNode node)
    {
        GraphNode foundStartingNode = null;
        for(GraphNode g : node.children)
        {
            if(g.node.toString().equals(startingNode.toString())&&g.node.getStartPosition()==startingNode.getStartPosition())
            {
                return g;
            }
            foundStartingNode = getStartingNode(g);

            if(foundStartingNode!=null)
                break;
        }
        return foundStartingNode;
    }

    void recursionForBackwardSlicing (GraphNode g)
    {
        if(g.equals(root))
            return;

        nodesForBackwardSlicing.add(g.node);

        for(GraphNode gg : g.parents)
        {
            recursionForBackwardSlicing(gg);
        }
    }

    public void parser (String str) {

        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setResolveBindings(true);
        parser.setSource(str.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        //parser.setEnvironment(new String[] {"C:\\Users\\ASUS\\Desktop\\demo\\out\\production\\demo"}, new String[] {"C:\\Users\\ASUS\\Desktop\\demo\\src"}, null, true);
        parser.setEnvironment(null, null, null, true);
        parser.setUnitName("Saal.java");
        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        cu.accept(new ASTVisitor() {

            public void preVisit (ASTNode node) {
                if(node instanceof Statement && !(node instanceof Block))
                {
                    startingNode = node;
                    GraphNode foundNode = getStartingNode(root);
                    System.out.println(foundNode.node);
                    recursionForBackwardSlicing(foundNode);
                    recursionForForwardSlicing(foundNode);
                    System.out.println("Backward slicing:");
                    for(ASTNode astNode: nodesForBackwardSlicing)
                    {
                        System.out.println(astNode);
                    }
                    System.out.println("Forward slicing:");
                    for(ASTNode astNode: nodesForForwardSlicing)
                    {
                        System.out.println(astNode);
                    }
                    System.out.println("---------------------------------");
                    nodesForBackwardSlicing.clear();
                    nodesForForwardSlicing.clear();
                }
            }

        });
    }

    public void operations () {
        try {
            parse(readFileToString("src/sourcePackage/Saal.java"));

            //kut(root);

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try {
            parser(readFileToString("src/sourcePackage/Saal.java"));

            //kut(root);

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*for(ASTNode n : nodes)
        {
            System.out.println(n);
        }*/

        printTree(root,"");

    }

    public void printTree (GraphNode currentRoot, String indent)
    {
        System.out.print(indent);
        System.out.println(currentRoot.node);

        for(GraphNode child: currentRoot.children)
        {
            printTree(child, indent.concat("----"));
        }
    }
}
